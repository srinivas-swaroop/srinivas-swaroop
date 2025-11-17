Here is your **Lab 6** written in the same clear, clean CN Lab Record format as your other labs 👇
(Everything short, neat, exam-ready)

---

# **Lab 6: Port-Security Configuration to Prevent MAC Flooding Attack**

### **Aim:**

To configure switch port security to protect the network from MAC flooding attacks by limiting and controlling the MAC addresses allowed on switch ports.

---

### **Objectives:**

1. To build a basic LAN topology with two PCs connected to a switch.
2. To configure **port-security** on switch interfaces.
3. To restrict authorized MAC addresses using static/sticky learning.
4. To apply port-security violation modes and verify the behavior.

---

### **Theory / Background:**

Switch Port Security is a security mechanism used to associate specific **MAC addresses** with specific **switch interfaces**.
It prevents attackers from performing **MAC flooding attacks**, which overflow the switch MAC table and force the switch into hub-like behavior.

Port Security allows:

* Limiting number of MAC addresses on a port
* Allowing specific MAC addresses (static or sticky)
* Applying violation actions when unauthorized devices connect

**Violation Modes:**

* **protect** → drop unauthorized traffic silently
* **restrict** → drop traffic + send log messages
* **shutdown** → disable the port (default and most secure)

**Sticky MAC:**
Automatically learns the MAC of the connected device and stores it.

---

### **Network Topology:**

* **PC1 → Switch Fa0/1**
* **PC2 → Switch Fa0/2**

---

### **Brief Procedure:**

### **1. Build the topology**

Connect PC1 to **Fa0/1** and PC2 to **Fa0/2**.

---

### **2. Configure Port Security**

#### **Step A – Set interface as ACCESS**

```
Switch(config)# interface fa0/1
Switch(config-if)# switchport mode access

Switch(config)# interface fa0/2
Switch(config-if)# switchport mode access
```

#### **Step B – Enable Port Security**

```
Switch(config-if)# switchport port-security
```

#### **Step C – Enable Sticky MAC (auto-learn)**

```
Switch(config-if)# switchport port-security mac-address sticky
```

#### **Optional Commands:**

**Set maximum MAC addresses:**

```
Switch(config-if)# switchport port-security maximum 1
```

**Set violation mode:**

```
Switch(config-if)# switchport port-security violation shutdown
```

*(Can also use `protect` or `restrict`)*

---

### **3. Verification Commands**

```
Switch# show port-security
Switch# show port-security interface fa0/1
Switch# show mac-address-table
Switch# show ip interface brief
```

---

### **Observations:**

* The switch learned MAC addresses using sticky mode.
* When an unauthorized device was connected, the violation action was triggered.
* Only the allowed MAC address on each port was able to communicate.
* Port-security status showed correct configuration and learned MAC entries.

---

### **Conclusion:**

Port Security was successfully implemented on switch interfaces to prevent MAC flooding attacks.
Authorized hosts accessed the network normally, while unauthorized devices were blocked based on violation settings.
The network became more secure and stable with proper port-security configuration.

---

If you want, I can now **format all labs (1–10) together in one uniform style** for your complete CN Record.
