package HospitalManageProject;

import java.util.ArrayList;
import java.util.Scanner;

class Patient {
    private static int idCounter = 1;
    private int id;
    private String name;
    private int age;
    private String gender;

    public Patient(String name, String gender, int age) {
        this.id = idCounter++;
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String toString() {
        return "Patient Id: " + id + ", Name: " + name + ", Gender: " + gender + ", Age: " + age;
    }
}

class Doctor {
    private static int idCounter = 1;
    private int id;
    private String name;
    private String speciality;

    public Doctor(String name, String speciality) {
        this.id = idCounter++;
        this.name = name;
        this.speciality = speciality;
    }

    public int getId() {
        return id;
    }

    public String toString() {
        return "Doctor Id: " + id + ", Name: " + name + ", Speciality: " + speciality;
    }
}

class Appointment {
    private Patient patient;
    private Doctor doctor;
    private String date;

    public Appointment(Patient patient, Doctor doctor, String date) {
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
    }

    public String toString() {
        return "Appointment [Patient: " + patient + ", Doctor: " + doctor + ", Date: " + date + "]";
    }
}

public class HospitalManageProject {
    private static ArrayList<Patient> patients = new ArrayList<>();
    private static ArrayList<Doctor> doctors = new ArrayList<>();
    private static ArrayList<Appointment> appointments = new ArrayList<>();

    private static void viewPatients() {
        System.out.println("\n--- List of Patients ---");
        if (patients.isEmpty()) {
            System.out.println("No patients available.");
        } else {
            for (Patient p : patients) {
                System.out.println(p);
            }
        }
    }

    private static void viewDoctors() {
        System.out.println("\n--- List of Doctors ---");
        if (doctors.isEmpty()) {
            System.out.println("No doctors available.");
        } else {
            for (Doctor d : doctors) {
                System.out.println(d);
            }
        }
    }

    private static void viewAppointments() {
        System.out.println("\n--- List of Appointments ---");
        if (appointments.isEmpty()) {
            System.out.println("No appointments scheduled.");
        } else {
            for (Appointment a : appointments) {
                System.out.println(a);
            }
        }
    }

    private static void addPatient(Scanner scanner) {
        System.out.print("Enter Patient Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Patient Age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // consume newline

        System.out.print("Enter Patient Gender: ");
        String gender = scanner.nextLine();

        Patient patient = new Patient(name, gender, age);
        patients.add(patient);
        System.out.println("Patient Added Successfully!");
    }

    private static void addDoctor(Scanner scanner) {
        System.out.print("Enter Doctor Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Doctor Speciality: ");
        String speciality = scanner.nextLine();

        Doctor doctor = new Doctor(name, speciality);
        doctors.add(doctor);
        System.out.println("Doctor Added Successfully!");
    }

    private static void scheduleAppointment(Scanner scanner) {
        if (patients.isEmpty() || doctors.isEmpty()) {
            System.out.println("Please add at least one patient and one doctor first.");
            return;
        }

        System.out.println("\nSelect Patient by ID:");
        for (Patient p : patients) {
            System.out.println(p.getId() + " → " + p);
        }
        int patientId = scanner.nextInt();
        scanner.nextLine();

        Patient selectedPatient = null;
        for (Patient p : patients) {
            if (p.getId() == patientId) {
                selectedPatient = p;
                break;
            }
        }
        if (selectedPatient == null) {
            System.out.println("Invalid Patient ID!");
            return;
        }

        System.out.println("\nSelect Doctor by ID:");
        for (Doctor d : doctors) {
            System.out.println(d.getId() + " → " + d);
        }
        int doctorId = scanner.nextInt();
        scanner.nextLine();

        Doctor selectedDoctor = null;
        for (Doctor d : doctors) {
            if (d.getId() == doctorId) {
                selectedDoctor = d;
                break;
            }
        }
        if (selectedDoctor == null) {
            System.out.println("Invalid Doctor ID!");
            return;
        }

        System.out.print("Enter Appointment Date (DD-MM-YYYY): ");
        String date = scanner.nextLine();

        appointments.add(new Appointment(selectedPatient, selectedDoctor, date));
        System.out.println("Appointment Scheduled Successfully!");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== Hospital Management System =====");
            System.out.println("1. Add Patient");
            System.out.println("2. Add Doctor");
            System.out.println("3. Schedule Appointment");
            System.out.println("4. View Patients");
            System.out.println("5. View Doctors");
            System.out.println("6. View Appointments");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addPatient(scanner);
                    break;
                case 2:
                    addDoctor(scanner);
                    break;
                case 3:
                    scheduleAppointment(scanner);
                    break;
                case 4:
                    viewPatients();
                    break;
                case 5:
                    viewDoctors();
                    break;
                case 6:
                    viewAppointments();
                    break;
                case 0:
                    System.out.println("Exiting System... Thank You!");
                    break;
                default:
                    System.out.println("Invalid Choice! Try again.");
            }

        } while (choice != 0);

        scanner.close();
    }
}
