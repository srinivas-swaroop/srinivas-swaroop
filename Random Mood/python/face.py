import cv2
import face_recognition
import numpy as np
import os
import pickle
from datetime import datetime

# Folder to save student embeddings
if not os.path.exists("student_data"):
    os.makedirs("student_data")

LOG_FILE = "attendance_log.txt"

# Strict parameters
STRICT_THRESHOLD = 0.40
REQUIRED_CONSECUTIVE_MATCHES = 5

# ---------------- Register ---------------- #
def register_student(name, roll_no):
    cap = cv2.VideoCapture(0)
    print("Look at the camera. Press 's' to capture your face.")

    while True:
        ret, frame = cap.read()
        if not ret or frame is None:
            print("Failed to capture image from camera. Is the webcam busy?")
            continue

        cv2.imshow("Registration", frame)
        key = cv2.waitKey(1)

        if key == ord('s'):
            rgb_frame = cv2.cvtColor(frame.astype("uint8"), cv2.COLOR_BGR2RGB)

            face_locations = face_recognition.face_locations(rgb_frame, model="hog")
            if len(face_locations) == 0:
                print("No face detected. Try again.")
                continue

            face_encoding = face_recognition.face_encodings(rgb_frame, face_locations)[0]

            file_path = f"student_data/{roll_no}.pkl"
            with open(file_path, "wb") as f:
                pickle.dump({"name": name, "roll_no": roll_no, "embedding": face_encoding}, f)

            print(f"Student {name} registered successfully! Embedding saved at {file_path}")
            break

        elif key == ord('q'):
            print("Registration cancelled.")
            break

    cap.release()
    cv2.destroyAllWindows()

# ---------------- Recognition with Strict Validation ---------------- #
def recognize_student():
    # Load embeddings
    known_encodings = []
    known_names = []
    known_rolls = []

    for file in os.listdir("student_data"):
        with open(os.path.join("student_data", file), "rb") as f:
            data = pickle.load(f)
            known_encodings.append(data["embedding"])
            known_names.append(data["name"])
            known_rolls.append(data["roll_no"])

    cap = cv2.VideoCapture(0)
    print("Press 'q' to quit recognition.")

    # Track consecutive matches per roll_no
    match_counters = {}

    while True:
        ret, frame = cap.read()
        if not ret:
            continue

        rgb_frame = cv2.cvtColor(frame, cv2.COLOR_BGR2RGB)

        face_locations = face_recognition.face_locations(rgb_frame, model="hog")
        face_encodings = face_recognition.face_encodings(rgb_frame, face_locations)

        for (top, right, bottom, left), face_encoding in zip(face_locations, face_encodings):
            distances = face_recognition.face_distance(known_encodings, face_encoding)

            if len(distances) > 0:
                min_dist_index = np.argmin(distances)
                min_dist = distances[min_dist_index]

                if min_dist < STRICT_THRESHOLD:
                    name = known_names[min_dist_index]
                    roll_no = known_rolls[min_dist_index]

                    # Initialize counter if not seen before
                    if roll_no not in match_counters:
                        match_counters[roll_no] = 0
                    match_counters[roll_no] += 1

                    # Draw bounding box + label
                    cv2.rectangle(frame, (left, top), (right, bottom), (0, 255, 0), 2)
                    cv2.putText(frame, f"{name} ({roll_no})", (left, top - 10),
                                cv2.FONT_HERSHEY_SIMPLEX, 0.7, (0, 255, 0), 2)

                    # If enough consecutive matches -> validate
                    if match_counters[roll_no] >= REQUIRED_CONSECUTIVE_MATCHES:
                        with open(LOG_FILE, "a") as log:
                            log.write(f"{datetime.now()} - {roll_no} - {name}\n")
                        print(f"✅ Strictly validated: {name} ({roll_no})")
                        match_counters[roll_no] = -9999  # Mark as already logged
                else:
                    # Face did not pass strict threshold
                    cv2.rectangle(frame, (left, top), (right, bottom), (0, 0, 255), 2)
                    cv2.putText(frame, "Unknown", (left, top - 10),
                                cv2.FONT_HERSHEY_SIMPLEX, 0.7, (0, 0, 255), 2)

        cv2.imshow("Recognition", frame)
        if cv2.waitKey(1) & 0xFF == ord('q'):
            break

    cap.release()
    cv2.destroyAllWindows()


# ---------------- Main ---------------- #
if __name__ == "__main__":
    print("1. Register student")
    print("2. Recognize student")
    choice = input("Enter choice: ")

    if choice == "1":
        name = input("Enter student name: ")
        roll_no = input("Enter roll number: ")
        register_student(name, roll_no)
    elif choice == "2":
        recognize_student()
