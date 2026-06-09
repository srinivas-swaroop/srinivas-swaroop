/*
=========================================================
SMART MULTI-SENSOR SYSTEM (ALL-IN-ONE)

This program uses:
- PIR (motion)
- Ultrasonic (distance)
- DHT22 (temperature + humidity)
- Gas sensor (MQ2)
- LDR (light)
- LM35 (temperature)
- Potentiometer (manual control)
- LED (output)
- Buzzer (alert)
- Relay (appliance control)
- Servo motor (angle control)
- LCD (display)

=========================================================
*/

#include <DHT.h>
#include <LiquidCrystal_I2C.h>
#include <Servo.h>

// -------- PIN DEFINITIONS --------
#define PIR 2
#define TRIG 3
#define ECHO 4
#define DHTPIN 5
#define BUZZER 6
#define RELAY 7
#define SERVO_PIN 8
#define LED 9

#define GAS A0
#define LM35 A1
#define LDR A2
#define POT A3

// -------- OBJECTS --------
DHT dht(DHTPIN, DHT22);
LiquidCrystal_I2C lcd(0x27, 16, 2);
Servo servo;

// -------- VARIABLES --------
int thresholdGas = 400;

void setup() {
  Serial.begin(9600);

  // Input pins
  pinMode(PIR, INPUT);
  pinMode(ECHO, INPUT);

  // Output pins
  pinMode(TRIG, OUTPUT);
  pinMode(BUZZER, OUTPUT);
  pinMode(RELAY, OUTPUT);
  pinMode(LED, OUTPUT);

  // Start devices
  dht.begin();
  lcd.init();
  lcd.backlight();
  servo.attach(SERVO_PIN);
}

void loop() {

  // =====================================================
  // 1. PIR SENSOR (MOTION DETECTION)
  // =====================================================
  int motion = digitalRead(PIR);

  if (motion == HIGH) {
    digitalWrite(RELAY, HIGH); // turn ON appliance
  } else {
    digitalWrite(RELAY, LOW);
  }

  // =====================================================
  // 2. GAS SENSOR (MQ2)
  // =====================================================
  int gasValue = analogRead(GAS);

  if (gasValue > thresholdGas) {
    digitalWrite(BUZZER, HIGH); // alert
  } else {
    digitalWrite(BUZZER, LOW);
  }

  // =====================================================
  // 3. LM35 TEMPERATURE SENSOR
  // =====================================================
  int tempVal = analogRead(LM35);

  // Convert analog to Celsius
  float tempLM35 = tempVal * 0.488;

  // =====================================================
  // 4. LDR (LIGHT SENSOR)
  // =====================================================
  int light = analogRead(LDR);

  // If dark → turn ON LED
  if (light < 300) {
    analogWrite(LED, 200);
  } else {
    analogWrite(LED, 0);
  }

  // =====================================================
  // 5. POTENTIOMETER (MANUAL INPUT)
  // =====================================================
  int potValue = analogRead(POT);

  // Map value (0–1023 → 0–180 for servo)
  int angle = map(potValue, 0, 1023, 0, 180);

  // Move servo
  servo.write(angle);

  // =====================================================
  // 6. ULTRASONIC SENSOR
  // =====================================================
  digitalWrite(TRIG, LOW);
  delayMicroseconds(2);

  digitalWrite(TRIG, HIGH);
  delayMicroseconds(10);
  digitalWrite(TRIG, LOW);

  long duration = pulseIn(ECHO, HIGH);

  // Convert to distance (cm)
  float distance = duration * 0.034 / 2;

  // =====================================================
  // 7. DHT22 SENSOR
  // =====================================================
  float tempDHT = dht.readTemperature();
  float humidity = dht.readHumidity();

  // =====================================================
  // 8. LCD DISPLAY
  // =====================================================
  lcd.clear();

  lcd.setCursor(0, 0);
  lcd.print("T:");
  lcd.print(tempDHT);
  lcd.print(" H:");
  lcd.print(humidity);

  lcd.setCursor(0, 1);
  lcd.print("D:");
  lcd.print(distance);

  // =====================================================
  // 9. SERIAL MONITOR OUTPUT
  // =====================================================
  Serial.println("----DATA----");
  Serial.print("Motion: "); Serial.println(motion);
  Serial.print("Gas: "); Serial.println(gasValue);
  Serial.print("LM35 Temp: "); Serial.println(tempLM35);
  Serial.print("Light: "); Serial.println(light);
  Serial.print("Distance: "); Serial.println(distance);
  Serial.print("Servo Angle: "); Serial.println(angle);

  delay(2000);
}