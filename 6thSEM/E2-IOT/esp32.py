# ==========================================================
# 🔥 SMART IoT MULTI-SENSOR SYSTEM (ESP32 + CLOUD)
# ==========================================================
# This program demonstrates:
# - Multiple sensor readings (input)
# - Actuator control (output)
# - OLED display (user interface)
# - Cloud integration (send data to server)
#
# ==========================================================

from machine import Pin, ADC, PWM, I2C, time_pulse_us
import time
import dht
import network
import urequests   # Used for HTTP cloud communication
from ssd1306 import SSD1306_I2C

# ==========================================================
# 🌐 WIFI SETUP (CLOUD CONNECTION)
# ==========================================================
# ESP32 connects to internet to send data

ssid = "YOUR_WIFI_NAME"
password = "YOUR_PASSWORD"

wifi = network.WLAN(network.STA_IF)
wifi.active(True)
wifi.connect(ssid, password)

print("Connecting to WiFi...")
while not wifi.isconnected():
    time.sleep(1)

print("Connected:", wifi.ifconfig())

# ==========================================================
# 📌 PIN SETUP (SAFE ESP32 PINS)
# ==========================================================

# PIR Motion Sensor → Digital Input
pir = Pin(13, Pin.IN)

# Ultrasonic Sensor → Digital Output + Input
trig = Pin(5, Pin.OUT)
echo = Pin(18, Pin.IN)

# DHT22 → Digital Sensor
dht_sensor = dht.DHT22(Pin(4))

# Actuators
buzzer = Pin(15, Pin.OUT)
relay = Pin(23, Pin.OUT)

# LED using PWM (brightness control)
led = PWM(Pin(2))
led.freq(1000)

# Servo Motor (PWM)
servo = PWM(Pin(27))
servo.freq(50)

# Analog Sensors (ADC)
gas = ADC(Pin(34))
lm35 = ADC(Pin(35))
ldr = ADC(Pin(32))
pot = ADC(Pin(33))

# Set ADC range (ESP32 uses 0–3.3V)
gas.atten(ADC.ATTN_11DB)
lm35.atten(ADC.ATTN_11DB)
ldr.atten(ADC.ATTN_11DB)
pot.atten(ADC.ATTN_11DB)

# OLED Display (I2C Communication)
i2c = I2C(0, scl=Pin(22), sda=Pin(21))
oled = SSD1306_I2C(128, 64, i2c)

# Threshold for gas alert
thresholdGas = 30000

# ==========================================================
# 📏 FUNCTION: ULTRASONIC DISTANCE MEASUREMENT
# ==========================================================
def get_distance():
    # Send trigger pulse
    trig.low()
    time.sleep_us(2)
    trig.high()
    time.sleep_us(10)
    trig.low()

    # Measure echo time
    duration = time_pulse_us(echo, 1)

    # Convert to distance (cm)
    distance = (duration * 0.034) / 2
    return distance

# ==========================================================
# ☁️ FUNCTION: SEND DATA TO CLOUD
# ==========================================================
def send_to_cloud(data):
    try:
        # Example API (Replace with your server / ThingSpeak / Firebase)
        url = "http://api.thingspeak.com/update?api_key=YOUR_API_KEY"

        # Send data as parameters
        full_url = url + "&field1={}&field2={}&field3={}".format(
            data["temp"], data["gas"], data["distance"]
        )

        response = urequests.get(full_url)
        response.close()

        print("Data sent to cloud!")

    except Exception as e:
        print("Cloud Error:", e)

# ==========================================================
# 🔁 MAIN LOOP
# ==========================================================
while True:

    # ------------------------------------------------------
    # 1️⃣ PIR SENSOR (MOTION DETECTION)
    # ------------------------------------------------------
    motion = pir.value()   # 1 = motion, 0 = no motion
    relay.value(motion)    # Turn ON relay if motion detected

    # ------------------------------------------------------
    # 2️⃣ GAS SENSOR (MQ-2)
    # ------------------------------------------------------
    gas_val = gas.read()   # Analog value (0–4095)
    buzzer.value(1 if gas_val > thresholdGas else 0)

    # ------------------------------------------------------
    # 3️⃣ LM35 TEMPERATURE SENSOR
    # ------------------------------------------------------
    voltage = lm35.read() * 3.3 / 4095
    temp_lm35 = voltage * 100   # 10mV per °C

    # ------------------------------------------------------
    # 4️⃣ LDR (LIGHT SENSOR)
    # ------------------------------------------------------
    light = ldr.read()
    led.duty(800 if light < 1000 else 0)  # Dark → LED ON

    # ------------------------------------------------------
    # 5️⃣ POTENTIOMETER → SERVO CONTROL
    # ------------------------------------------------------
    pot_val = pot.read()

    # Map 0–4095 → servo duty range
    duty = int(40 + (pot_val / 4095) * 115)
    servo.duty(duty)

    # ------------------------------------------------------
    # 6️⃣ ULTRASONIC SENSOR
    # ------------------------------------------------------
    distance = get_distance()

    # ------------------------------------------------------
    # 7️⃣ DHT22 SENSOR
    # ------------------------------------------------------
    try:
        dht_sensor.measure()
        temp_dht = dht_sensor.temperature()
        humidity = dht_sensor.humidity()
    except:
        temp_dht = 0
        humidity = 0

    # ------------------------------------------------------
    # 8️⃣ OLED DISPLAY OUTPUT
    # ------------------------------------------------------
    oled.fill(0)
    oled.text("Temp:{}C".format(temp_dht), 0, 0)
    oled.text("Hum:{}%".format(humidity), 0, 10)
    oled.text("Gas:{}".format(gas_val), 0, 20)
    oled.text("Dist:{:.1f}cm".format(distance), 0, 30)
    oled.show()

    # ------------------------------------------------------
    # 9️⃣ SERIAL MONITOR (DEBUGGING)
    # ------------------------------------------------------
    print("Motion:", motion)
    print("Gas:", gas_val)
    print("Temp LM35:", temp_lm35)
    print("Distance:", distance)

    # ------------------------------------------------------
    # 🔟 CLOUD DATA SEND
    # ------------------------------------------------------
    data = {
        "temp": temp_dht,
        "gas": gas_val,
        "distance": distance
    }

    send_to_cloud(data)

    # Wait before next cycle
    time.sleep(5)