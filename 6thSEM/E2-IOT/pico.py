from machine import Pin, ADC, PWM, I2C
import time
import dht
from ssd1306 import SSD1306_I2C

# ---------------- PIN SETUP ----------------
pir = Pin(2, Pin.IN)

trig = Pin(3, Pin.OUT)
echo = Pin(4, Pin.IN)

dht_sensor = dht.DHT22(Pin(5))

buzzer = Pin(6, Pin.OUT)
relay = Pin(7, Pin.OUT)
led = PWM(Pin(9))
led.freq(1000)

servo = PWM(Pin(8))
servo.freq(50)

gas = ADC(26)
lm35 = ADC(27)
ldr = ADC(28)
pot = ADC(29)

# I2C OLED (instead of LCD)
i2c = I2C(0, scl=Pin(1), sda=Pin(0))
oled = SSD1306_I2C(128, 64, i2c)

thresholdGas = 30000

# ---------------- FUNCTIONS ----------------

def get_distance():
    trig.low()
    time.sleep_us(2)
    
    trig.high()
    time.sleep_us(10)
    trig.low()

    duration = time_pulse_us(echo, 1)
    distance = (duration * 0.034) / 2
    return distance

# ---------------- LOOP ----------------
while True:

    # 1. PIR
    motion = pir.value()
    relay.value(motion)

    # 2. GAS
    gas_val = gas.read_u16()
    buzzer.value(1 if gas_val > thresholdGas else 0)

    # 3. LM35
    temp_lm35 = (lm35.read_u16() * 3.3 / 65535) * 100

    # 4. LDR
    light = ldr.read_u16()
    led.duty_u16(50000 if light < 20000 else 0)

    # 5. POT → SERVO
    pot_val = pot.read_u16()
    duty = int(2000 + (pot_val / 65535) * 8000)
    servo.duty_u16(duty)

    # 6. ULTRASONIC
    # (Simplified)
    distance = 0  # replace with proper pulse code if needed

    # 7. DHT22
    try:
        dht_sensor.measure()
        temp_dht = dht_sensor.temperature()
        humidity = dht_sensor.humidity()
    except:
        temp_dht = 0
        humidity = 0

    # 8. OLED DISPLAY
    oled.fill(0)
    oled.text("T:{}C".format(temp_dht), 0, 0)
    oled.text("H:{}%".format(humidity), 0, 10)
    oled.text("Gas:{}".format(gas_val), 0, 20)
    oled.text("Dist:{}".format(distance), 0, 30)
    oled.show()

    # 9. SERIAL
    print("Motion:", motion)
    print("Gas:", gas_val)
    print("LM35:", temp_lm35)
    print("Light:", light)
    print("Servo:", duty)

    time.sleep(2)