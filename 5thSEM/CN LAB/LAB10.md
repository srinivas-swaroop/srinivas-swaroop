Here’s your **Lab Record Content (Lab 10)** neatly formatted and concise — perfect for your CN Lab record file 👇

---

## **Lab 10: Design and Configuring WAN using Three Routers with Static Routing**

### **Aim:**

To design and configure a Wide Area Network (WAN) using three routers with static routing and verify connectivity between all end devices.

---

### **Objectives:**

1. To design a WAN topology with **three routers**, **three switches**, and **six PCs**.
2. To configure **IP addressing** on all devices as per the addressing table.
3. To configure **static routing** on all routers for inter-network communication.
4. To verify the **end-to-end connectivity** using simulation tools (e.g., Packet Tracer).

---

### **Brief Procedure:**

1. **Design the topology:**
   Connect three routers in a serial link forming a WAN. Each router connects to a LAN (with a switch and two PCs).

2. **Assign IP addresses:**
   Configure the IP addresses on PCs, router interfaces, and serial links according to the given addressing table.

3. **Configure static routes:**
   On each router, define static routes to reach the networks connected to other routers using the `ip route` command.

4. **Verify the configuration:**

   * Check connectivity between routers using the `ping` command.
   * Verify end-to-end communication between PCs in different networks.
   * Save the configuration using `copy run start`.

---

### **Observations:**

* After configuring IP addresses and static routes, each PC in one LAN was able to communicate with PCs in the other LANs.
* The **ping results** showed successful replies indicating correct static routing configuration.
* Routing tables on each router displayed the correct static entries for remote networks.

---

### **Conclusion:**

Static routing was successfully implemented on a WAN consisting of three routers.
All devices across different networks communicated effectively, confirming proper configuration of IP addressing and static routes.

---

Would you like me to make it **shorter (for record book)** or **formatted like a report (for submission PDF)**?
