Here’s your **Lab Record Content for Lab 4** written clearly and in the same format style as your other labs 👇

---

## **Lab 4: Design of a MAN Network using a Single Router and Configuration through CLI Mode**

### **Aim:**

To design a Metropolitan Area Network (MAN) using a single router to connect two LANs and configure the router using CLI mode to enable communication between them.

---

### **Objectives:**

1. To design **two LANs** using two switches, each connected with **three PCs**.
2. To connect both LANs using a **single router**.
3. To configure the router using **CLI mode**.
4. To verify the **connectivity** between the two LANs.

---

### **Addressing Table:**

| **Device** | **Interface**    | **IP Address** | **Subnet Mask** |
| ---------- | ---------------- | -------------- | --------------- |
| PC1        | NIC              | 192.168.1.5    | 255.255.255.0   |
| PC2        | NIC              | 192.168.1.6    | 255.255.255.0   |
| PC3        | NIC              | 192.168.1.7    | 255.255.255.0   |
| PC4        | NIC              | 192.168.2.5    | 255.255.255.0   |
| PC5        | NIC              | 192.168.2.6    | 255.255.255.0   |
| PC6        | NIC              | 192.168.2.7    | 255.255.255.0   |
| Router 1   | FastEthernet 0/0 | 192.168.1.1    | 255.255.255.0   |
| Router 1   | FastEthernet 0/1 | 192.168.2.1    | 255.255.255.0   |
| LAN 1      | —                | 192.168.1.0    | 255.255.255.0   |
| LAN 2      | —                | 192.168.2.0    | 255.255.255.0   |

---

### **Brief Procedure:**

1. **Design the topology:**
   Connect two switches to a single router. Attach three PCs to each switch forming two LANs.

2. **Assign IP addresses:**
   Configure IP addresses on all PCs and router interfaces as per the addressing table.

3. **Configure Router using CLI:**

   * Enter global configuration mode:

     ```
     Router> enable
     Router# configure terminal
     ```
   * Assign IP addresses to router interfaces:

     ```
     Router(config)# interface fa0/0
     Router(config-if)# ip address 192.168.1.1 255.255.255.0
     Router(config-if)# no shutdown
     Router(config)# interface fa0/1
     Router(config-if)# ip address 192.168.2.1 255.255.255.0
     Router(config-if)# no shutdown
     ```
   * Exit configuration mode and save the settings.

4. **Verify connectivity:**
   Use the `ping` command between PCs from different LANs to check successful communication.

---

### **Observations:**

* The router interfaces were configured correctly using the CLI.
* PCs within each LAN could communicate with one another.
* PCs from LAN 1 successfully pinged PCs in LAN 2 through the router.

---

### **Conclusion:**

A MAN network was successfully designed and implemented using a single router connecting two LANs.
Router configuration through CLI enabled communication between both LANs, confirming proper inter-network connectivity.

---

Would you like me to make the **Lab 5** format next (so it matches your lab record style)?
