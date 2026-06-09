# =========================================================
# SMART MULTI-SENSOR SYSTEM - RASPBERRY PI 4 VERSION
# =========================================================

# ----------- IMPORT LIBRARIES -----------
import RPi.GPIO as GPIO          # GPIO control
import time                      # delay
import Adafruit_DHT              # DHT22 sensor
import spidev                    # SPI for MCP3008 ADC
from luma.core.interface.serial import i2c
from luma.oled.device import ssd1306
from PIL import ImageDraw

# ----------- GPIO SETUP -----------
GPIO.setmode(GPIO.BCM)

# PIR Sensor (motion detection input)
pir = 2
GPIO.setup(pir, GPIO.IN)

# Ultrasonic Sensor Pins
TRIG = 3
ECHO = 4
GPIO.setup(TRIG, GPIO.OUT)
GPIO.setup(ECHO, GPIO.IN)

# Buzzer, Relay, LED (output devices)
buzzer = 6
relay = 7
led = 9

GPIO.setup(buzzer, GPIO.OUT)
GPIO.setup(relay, GPIO.OUT)
GPIO.setup(led, GPIO.OUT)

# Servo (PWM control)
servo_pin = 8
GPIO.setup(servo_pin, GPIO.OUT)
servo = GPIO.PWM(servo_pin, 50)   # 50Hz for servo
servo.start(0)

# ----------- DHT SENSOR -----------
DHT_SENSOR = Adafruit_DHT.DHT22
DHT_PIN = 5

# ----------- ADC (MCP3008 via SPI) -----------
spi = spidev.SpiDev()
spi.open(0, 0)   # bus 0, device 0

# Function to read analog data from MCP3008
def read_adc(channel):
    # MCP3008 expects 3 bytes
    adc = spi.xfer2([1, (8 + channel) << 4, 0])
    # Combine result into 10-bit value
    data = ((adc[1] & 3) << 8) + adc[2]
    return data

# ----------- OLED SETUP -----------
serial = i2c(port=1, address=0x3C)
oled = ssd1306(serial)
draw = ImageDraw.Draw(oled)

# ----------- ULTRASONIC FUNCTION -----------
def get_distance():
    GPIO.output(TRIG, False)
    time.sleep(0.0002)

    GPIO.output(TRIG, True)
    time.sleep(0.00001)
    GPIO.output(TRIG, False)

    # Wait for echo start
    while GPIO.input(ECHO) == 0:
        pulse_start = time.time()

    # Wait for echo end
    while GPIO.input(ECHO) == 1:
        pulse_end = time.time()

    # Calculate time difference
    duration = pulse_end - pulse_start

    # Distance formula (speed of sound)
    distance = duration * 17150
    return round(distance, 2)

# ----------- MAIN LOOP -----------
try:
    while True:

        # 1️⃣ PIR SENSOR
        motion = GPIO.input(pir)
        GPIO.output(relay, motion)  # Turn relay ON/OFF

        # 2️⃣ GAS SENSOR (ADC channel 0)
        gas_val = read_adc(0)
        GPIO.output(buzzer, gas_val > 300)

        # 3️⃣ LM35 (ADC channel 1)
        lm35_val = read_adc(1)
        voltage = lm35_val * (3.3 / 1023)
        temp_lm35 = voltage * 100

        # 4️⃣ LDR (ADC channel 2)
        light = read_adc(2)
        GPIO.output(led, light < 300)

        # 5️⃣ POTENTIOMETER → SERVO (ADC channel 3)
        pot_val = read_adc(3)
        duty = 2 + (pot_val / 1023) * 10
        servo.ChangeDutyCycle(duty)

        # 6️⃣ ULTRASONIC
        distance = get_distance()

        # 7️⃣ DHT22
        humidity, temp_dht = Adafruit_DHT.read_retry(DHT_SENSOR, DHT_PIN)

        # 8️⃣ OLED DISPLAY
        oled.clear()
        draw.text((0, 0), f"T:{temp_dht}C", fill=255)
        draw.text((0, 10), f"H:{humidity}%", fill=255)
        draw.text((0, 20), f"Gas:{gas_val}", fill=255)
        draw.text((0, 30), f"Dist:{distance}", fill=255)
        oled.display()

        # 9️⃣ SERIAL OUTPUT
        print("Motion:", motion)
        print("Gas:", gas_val)
        print("LM35:", temp_lm35)
        print("Light:", light)
        print("Servo Duty:", duty)

        time.sleep(2)

except KeyboardInterrupt:
    GPIO.cleanup()