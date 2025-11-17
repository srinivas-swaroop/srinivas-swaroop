Here’s your **Lab Record Content for Lab 5: Creating and Configuring Two VLANs** — written clearly, structured like your previous labs 👇

---

## **Lab 5: Creating and Configuring Two VLANs**

### **Aim:**

To create and configure two VLANs on switches, assign ports to VLANs, and verify communication between devices in the same VLAN.

---

### **Objectives:**

1. To verify the **default VLAN configuration** on switches.
2. To **create and configure VLANs** on the switches.
3. To **assign VLANs to ports** and test communication.
4. To create a **VLAN trunk** between switches for inter-switch VLAN communication.

---

### **Background / Scenario:**

Modern networks use **Virtual Local Area Networks (VLANs)** to divide a large broadcast domain into smaller, logical segments.
VLANs enhance both **performance** and **security** by controlling which hosts can communicate with each other.
When multiple switches are connected, **VLAN trunks** are used to carry traffic from multiple VLANs across a single link while preserving VLAN separation.

In this lab, VLANs will be created on two switches, assigned to specific ports, and tested for connectivity. A VLAN trunk will be configured to allow communication between devices in the same VLAN but on different switches.

---

### **Addressing Table:**

(*IP addressing will depend on VLANs created – a sample example is shown below for record clarity.*)

| **Device** | **Interface** | **IP Address** | **Subnet Mask** | **VLAN ID** |
| ---------- | ------------- | -------------- | --------------- | ----------- |
| PC1        | NIC           | 192.168.10.2   | 255.255.255.0   | VLAN 10     |
| PC2        | NIC           | 192.168.10.3   | 255.255.255.0   | VLAN 10     |
| PC3        | NIC           | 192.168.20.2   | 255.255.255.0   | VLAN 20     |
| PC4        | NIC           | 192.168.20.3   | 255.255.255.0   | VLAN 20     |

---

### **Brief Procedure:**

1. **Verify default VLAN configuration:**
   Use the command

   ```
   Switch# show vlan brief
   ```

   to view existing VLANs (default VLAN 1).

2. **Create VLANs:**

   ```
   Switch(config)# vlan 10  
   Switch(config-vlan)# name HR  
   Switch(config)# vlan 20  
   Switch(config-vlan)# name IT
   ```

3. **Assign ports to VLANs:**

   ```
   Switch(config)# interface fastEthernet 0/1  
   Switch(config-if)# switchport mode access  
   Switch(config-if)# switchport access vlan 10  

   Switch(config)# interface fastEthernet 0/2  
   Switch(config-if)# switchport mode access  
   Switch(config-if)# switchport access vlan 20
   ```

4. **Configure trunk link between switches:**

   ```
   Switch(config)# interface fastEthernet 0/24  
   Switch(config-if)# switchport mode trunk
   ```

5. **Verify configuration:**
   Use commands:

   ```
   show vlan brief  
   show interfaces trunk  
   ```

   and test **ping** between devices in the same VLAN.

---

### **Observations:**

* VLAN 10 and VLAN 20 were created successfully.
* Ports were correctly assigned to respective VLANs.
* Devices in the **same VLAN** communicated successfully.
* Devices in **different VLANs** could not communicate — confirming VLAN isolation.
* Trunk link between switches carried VLAN traffic properly.

---

### **Conclusion:**

Two VLANs were successfully created, configured, and tested on switches.
VLAN segmentation improved network management and security.
Trunking enabled communication between devices of the same VLAN across multiple switches.

---

Would you like me to make **Lab 6 (Inter-VLAN routing)** next in the same clean record style?
