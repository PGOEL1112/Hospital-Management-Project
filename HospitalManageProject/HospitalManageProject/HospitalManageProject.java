package HospitalManageProject;

import java.util.ArrayList;
import java.util.Scanner;

class Patient {
    private static int idCounter=1;
    private int id;
    private String name;
    private int age ;
    private String gender;

    public Patient(String name, String gender, int age) {
        this.id = idCounter++;
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    public int getId(){
        return id;
    }
    
    public String toString(){
        return "Patient Id : "+ id + ", Name : " + name +", Gender : " + gender + ", Age : " + age;
    } 
}

class Doctor{
    private static int idCounter=1;
    private int id;
    private String name;
    private String Speciality;

    public Doctor(String name, String Speciality) {
        this.id = idCounter++;
        this.name = name;
        this.Speciality=Speciality;
    }

    public int getId(){
        return id;
    }
    
    @Override
    public String toString(){
        return ("Doctor Id : "+ id + ", Name : " + name +", Speciality: " + Speciality);
    } 
}

class Appointment{
    private Patient patient;
    private Doctor doctor;
    private String date;

    public Appointment(Patient patient, Doctor doctor, String date) {
        this. patient = patient;
        this.doctor = doctor;
        this.date = date;

    }

    @Override
    public String toString(){
        return "Appointment : [Patient : " + patient + "Doctor : "+ doctor + "Date: " + date + "]";
    } 
}

public class HospitalManageProject{
    private static ArrayList<Patient> patients = new ArrayList<>();
    private static ArrayList<Doctor> doctors = new ArrayList<>();
    private static ArrayList<Appointment> appointments = new ArrayList<>();

    private static void ViewAppointment() {
        System.out.println("List of Appointments:");
        if (appointments.isEmpty()) {
            System.out.println("No appointments scheduled.");
        } else {
            for (Appointment a : appointments) {
                System.out.println(a);
            }
        }
    }

    private static void ViewDoctor() {
         System.out.println("List of Doctors:");
        if (doctors.isEmpty()) {
            System.out.println("No doctors available.");
        } else {
            for (Doctor d : doctors) {
                System.out.println(d);
            }
        }
    }

    private static void ViewPatient() {
        System.out.println("List of Patients:");
        if (patients.isEmpty()) {
            System.out.println("No patients available.");
        } else {
            for (Patient p : patients) {
                System.out.println(p);
            }
        }
    }

    private static void ScheduleAppointment(Scanner scanner) {
        if (patients.isEmpty() || doctors.isEmpty()) {
            System.out.println("Please ensure at least one patient and one doctor exist.");
            return;
        }

        System.out.println("Select Patient by ID:");
        for (Patient p : patients) {
            System.out.println(p.getId() + ". " + p);
        }
        int patientId = scanner.nextInt();
        scanner.nextLine(); // consume newline
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

        System.out.println("Select Doctor by ID:");
        for (Doctor d : doctors) {
            System.out.println(d.getId() + ". " + d);
        }
        int doctorId = scanner.nextInt();
        scanner.nextLine(); // consume newline
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

        Appointment appointment = new Appointment(selectedPatient, selectedDoctor, date);
        appointments.add(appointment);
        System.out.println("Appointment scheduled successfully!");
    }

    private static void AddDoctor(Scanner scanner) {
        System.out.print("Enter Doctor Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Doctor Speciality: ");
        String speciality = scanner.nextLine();

        Doctor doctor = new Doctor(name, speciality);
        doctors.add(doctor);
        System.out.println("Doctor added successfully!");
    }

    private static void AddPatient(Scanner scanner) {
       System.out.println("Enter the Patient Name: ");
        String name = scanner.nextLine();
        System.out.println("Enter the Patient Age : ");
        int age = scanner.nextInt();
        System.out.println("Enter the Patient Gender : ");
        String gender = scanner.nextLine();

        Patient patient= new Patient(name, gender, age);
        patients.add(patient);
        System.out.println("Patient Added Successfully !!");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int Choice;
    
        do{
            System.out.println("<--------------- Here we Presents our Hospital Management System ---------->");
            System.out.println();
            System.out.println("Select the choice that mentioned below ");
            System.out.println("-------------------------------------------");
            System.out.println("1. Add Patient ");
            System.out.println("2. Add Doctor ");
            System.out.println("3. Schedule a Appointment ");
            System.out.println("4. View Patient ");
            System.out.println("5. View Doctor");
            System.out.println("6. View Appointment ");
            System.out.println("0. Exit ");
            System.out.println("Please Select the choice : ");
            Choice = scanner.nextInt();

            switch (Choice) {
                case 1:
                    AddPatient(scanner);
                    break;
                case 2:
                    AddDoctor(scanner);
                    break;    
                case 3:
                    ScheduleAppointment(scanner);
                    break;
                case 4:
                    ViewPatient();
                    break;  
                case 5:
                    ViewDoctor();
                    break;    
                case 6:
                    ViewAppointment();
                    break;
                case 0:
                    System.out.println("EXIT FROM SYSTEM ...");    
                default:
                    System.out.println("Invalid Choice ! Please Enter a valid Choice..");
                    break;
            } 
        }while (Choice!=0);
    }
    
}
