package com.manasuraksha;

import com.manasuraksha.model.ThreatReport;
import com.manasuraksha.repository.FileReportRepository;
import com.manasuraksha.service.ReportService;
import com.manasuraksha.service.ThreatAnalyzer;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner INPUT = new Scanner(System.in);
    private static final ReportService SERVICE = new ReportService(
            new ThreatAnalyzer(), new FileReportRepository(Path.of("data", "reports.txt")));
    private static boolean telugu = false;

    public static void main(String[] args) {
        System.out.println("Mana Suraksha | Digital Safety Assistant");
        System.out.println("1. English  2. తెలుగు");
        telugu = readInt("Choose language / భాష ఎంచుకోండి: ") == 2;
        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt(message("Choose: ", "ఎంచుకోండి: "));
            try {
                switch (choice) {
                    case 1 -> analyzeSms();
                    case 2 -> analyzeLink();
                    case 3 -> analyzeCall();
                    case 4 -> viewReports();
                    case 5 -> backupReports();
                    case 0 -> {
                        running = false;
                        System.out.println(message("Stay safe online. Goodbye!", "ఆన్‌లైన్‌లో జాగ్రత్తగా ఉండండి. నమస్కారం!"));
                    }
                    default -> System.out.println(message("Choose a valid option.", "సరైన ఎంపికను ఎంచుకోండి."));
                }
            } catch (IllegalArgumentException exception) {
                System.out.println(message("Input error: ", "ఇన్‌పుట్ లోపం: ") + exception.getMessage());
            } catch (IOException exception) {
                System.out.println(message("Storage error: ", "డేటా నిల్వ లోపం: ") + exception.getMessage());
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n1. Analyze SMS / SMS సందేశం పరిశీలించండి");
        System.out.println("2. Check link / లింక్ పరిశీలించండి");
        System.out.println("3. Check call behaviour / కాల్ ప్రవర్తన పరిశీలించండి");
        System.out.println("4. View saved reports / నివేదికలు చూడండి");
        System.out.println("5. Backup reports / బ్యాకప్ తీసుకోండి");
        System.out.println("0. Exit / బయటకు వెళ్లండి");
    }

    private static void analyzeSms() throws IOException {
        System.out.print(message("Paste SMS text (up to 5000 chars): ", "SMS సందేశాన్ని నమోదు చేయండి (గరిష్ఠం 5000 అక్షరాలు): "));
        printReport(SERVICE.analyzeSms(INPUT.nextLine()));
    }

    private static void analyzeLink() throws IOException {
        System.out.print(message("Enter a URL: ", "లింక్ నమోదు చేయండి: "));
        printReport(SERVICE.analyzeLink(INPUT.nextLine()));
    }

    private static void analyzeCall() throws IOException {
        System.out.print(message("Describe what the caller asked (do not enter phone numbers): ", "కాల్ చేసిన వ్యక్తి ఏమి అడిగారో వివరించండి (ఫోన్ నంబర్లు నమోదు చేయవద్దు): "));
        String notes = INPUT.nextLine();
        System.out.print(message("Is this caller known to you? (y/n): ", "ఈ వ్యక్తి మీకు తెలుసా? (y/n): "));
        boolean known = INPUT.nextLine().trim().equalsIgnoreCase("y");
        printReport(SERVICE.analyzeCall(notes, known));
    }

    private static void viewReports() throws IOException {
        List<ThreatReport> reports = SERVICE.getReports();
        if (reports.isEmpty()) {
            System.out.println(message("No reports saved yet.", "ఇంకా నివేదికలు లేవు."));
            return;
        }
        reports.forEach(report -> System.out.println(
                report.createdAt() + " | " + report.category() + " | " +
                report.riskLevel() + " | " + report.reason()));
    }

    private static void backupReports() throws IOException {
        Path destination = Path.of("backups", "reports-" + System.currentTimeMillis() + ".txt");
        new FileReportRepository(Path.of("data", "reports.txt")).backupTo(destination);
        System.out.println(message("Backup created at: ", "బ్యాకప్ సృష్టించబడింది: ") + destination);
        System.out.println(message("Copy it to another device or trusted cloud storage.", "దీనిని మరో పరికరంలో లేదా నమ్మకమైన క్లౌడ్ నిల్వలో కాపీ చేయండి."));
    }

    private static void printReport(ThreatReport report) {
        System.out.println("\n" + message("Risk level: ", "ప్రమాద స్థాయి: ") + report.riskLevel());
        System.out.println(message("Reason: ", "కారణం: ") + report.reason());
        System.out.println(message("Report saved locally.", "నివేదిక స్థానికంగా సేవ్ చేయబడింది."));
        System.out.println(message("This is a rule-based warning, not a guaranteed scam detector.", "ఇది నియమాల ఆధారిత హెచ్చరిక మాత్రమే; మోసాన్ని ఖచ్చితంగా గుర్తించే సాధనం కాదు."));
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try { return Integer.parseInt(INPUT.nextLine().trim()); }
            catch (NumberFormatException exception) { System.out.println(message("Enter a number.", "సంఖ్యను నమోదు చేయండి.")); }
        }
    }

    private static String message(String english, String teluguText) {
        return telugu ? teluguText : english;
    }
}
