Here is your **Lab Record Content for Lab 7** written in a clean, neat, consistent format like your previous labs 👇
(Short, clear, perfect for your record book)

---

# **Lab 7: Router Serial Point-to-Point Connection using HDLC and PPP (PAP & CHAP)**

### **Aim:**

To design and configure a WAN using two routers with HDLC and PPP encapsulation, and implement PAP and CHAP authentication.

---

### **Objectives:**

1. To design a **WAN topology using two routers** connected via serial link.
2. To configure the routers with **HDLC** and **PPP encapsulation**.
3. To apply **PPP authentication** using **PAP** and **CHAP** methods.
4. To verify the **connectivity and authentication**.

---

### **Background / Theory:**

**HDLC (High-Level Data Link Control):**

* A WAN encapsulation protocol operating at **Data Link Layer**.
* Cisco HDLC is the default encapsulation on Cisco serial links.
* Cisco HDLC works **only between Cisco devices**.
* No authentication support → less secure.

**PPP (Point-to-Point Protocol):**

* Enhanced version of HDLC.
* Supports **authentication**, **error detection**, and **link quality monitoring**.
* Two Authentication Protocols:

  * **PAP (Password Authentication Protocol)** → 2-way handshake, passwords sent in clear text.
  * **CHAP (Challenge Handshake Authentication Protocol)** → 3-way handshake, uses **MD5 hash**, more secure.

---

### **Addressing Table:**

(*Values can be adjusted based on your Packet Tracer setup.*)

| **Device** | **Interface** | **IP Address** | **Subnet Mask** |
| ---------- | ------------- | -------------- | --------------- |
| Router 1   | Serial 0/0    | 10.0.0.1       | 255.255.255.0   |
| Router 2   | Serial 0/0    | 10.0.0.2       | 255.255.255.0   |
| Router 1   | LAN           | 192.168.1.1    | 255.255.255.0   |
| Router 2   | LAN           | 192.168.2.1    | 255.255.255.0   |

---

### **Brief Procedure:**

#### **1. Design the topology**

* Connect Router 1 and Router 2 via **Serial DCE–DTE cable**.
* Assign IP addresses on serial interfaces.

---

### **2. Configure HDLC (default)**

HDLC is default on Cisco routers.
To reapply manually:

```
Router(config)# interface s0/0
Router(config-if)# encapsulation hdlc
Router(config-if)# no shutdown
```

---

### **3. Configure PPP with PAP**

On **Router 1 (client):**

```
Router1(config)# interface s0/0
Router1(config-if)# encapsulation ppp
Router1(config-if)# ppp pap sent-username R1 password 123
```

On **Router 2 (server):**

```
Router2(config)# username R1 password 123
Router2(config)# interface s0/0
Router2(config-if)# encapsulation ppp
Router2(config-if)# ppp authentication pap
```

---

### **4. Configure PPP with CHAP**

On **both routers:**

```
Router1(config)# username R2 password cisco
Router2(config)# username R1 password cisco
```

Enable CHAP:

```
Router(config)# interface s0/0
Router(config-if)# encapsulation ppp
Router(config-if)# ppp authentication chap
```

---

### **5. Verify connection**

* Use:

```
show interfaces s0/0
show ppp all
ping 10.0.0.2
```

* Ensure authentication success messages like:
  **“CHAP authentication succeeded”**

---

### **Observations:**

* HDLC worked without authentication as it is the default encapsulation.
* PPP was successfully established between the two routers.
* PAP authentication showed clear-text password exchange.
* CHAP authentication used 3-way handshake and MD5 hashing.
* Communication was successful after authentication in both PAP and CHAP modes.

---

### **Conclusion:**

A WAN point-to-point serial connection was successfully created using HDLC and PPP.
PAP and CHAP authentication were configured and tested.
CHAP provided stronger security compared to PAP.
All configurations worked correctly, and connectivity was verified.

---

If you want, I can also prepare **Lab 6** or format **ALL labs together as a PDF-style record**.
