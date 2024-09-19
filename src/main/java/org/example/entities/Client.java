package org.example.entities;


    public class Client {
        private String name;
        private String address;
        private String phone;
        private boolean isProfessional;

        public Client(String name, String address, String phone, boolean isProfessional) {
            this.name = name;
            this.address = address;
            this.phone = phone;
            this.isProfessional = isProfessional;
        }

        // Getters and Setters
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public boolean isProfessional() {
            return isProfessional;
        }

        public void setProfessional(boolean isProfessional) {
            this.isProfessional = isProfessional;
        }
        public void displayClientInfo() {
            System.out.println("Client: " + name);
            System.out.println("Address: " + address);
            System.out.println("Phone: " + phone);
        }
    }


