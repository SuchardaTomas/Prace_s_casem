import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.Year;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import static java.time.Year.isLeap;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean start = true;

        System.out.println("Vítejte v aplikaci zjišťování přestupných roků");

        while(start != false) {
            System.out.println();
            System.out.println();
            System.out.println("Vyberte si jak chcete vybrat přestupný rok");
            System.out.println("-------------------------------------------------------");
            System.out.println("1. podle vašeho zadání roku");
            System.out.println("2. podle aktuálního data");
            System.out.println("3. vlastní formát data a času");
            System.out.println("4. Výpočet kolik zbývá času od zadaného datumu");
            System.out.println("5. Konec");

            int choose = sc.nextInt();

            switch(choose) {
                case 1 -> {
                    System.out.println("Zadejte rok");
                    int year = sc.nextInt();

                    Year firsYear = Year.of(year);

                    if(firsYear.isLeap() == true) {
                        System.out.println("rok je přestupný");
                    } else {
                        System.out.println("rok není přestupný");
                    }
                }

                case 2 -> {
                    int rok = Calendar.getInstance().get(Calendar.YEAR);
                    boolean nowYear = isLeap(rok);

                    if(nowYear) {
                        System.out.println("Aktuální rok " + rok + " je přestupný");
                    } else {
                        int nearestYear = rok;
                        while(!nowYear) {
                            nearestYear++;
                            nowYear = isLeap(nearestYear);
                        }
                        System.out.println("Sice tento rok " + rok + " přestupný není ale nejbližší přestupný rok je " + nearestYear);
                    }
                }

                case 3 -> {
                    long currentTimeMillis = System.currentTimeMillis();

                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

                    String formattedDateTime = dateFormat.format(new Date(currentTimeMillis));

                    System.out.println("Aktuální systémový čas: " + formattedDateTime);
                }

                case 4 -> {
                    System.out.print("Zadej den: ");
                    int den = sc.nextInt();
                    System.out.print("Zadej měsíc (číslo 1-12): ");
                    int mesic = sc.nextInt();
                    System.out.print("Zadej rok: ");
                    int rok = sc.nextInt();

                    LocalDateTime aktualniCas = LocalDateTime.now();

                    LocalDateTime cilovyCas = LocalDateTime.of(rok, mesic, den, 0, 0, 0);

                    Period period = Period.between(LocalDate.from(aktualniCas), LocalDate.from(cilovyCas));
                    long sekundyDoCile = ChronoUnit.SECONDS.between(aktualniCas, cilovyCas);

                    // Výstup
                    System.out.println("Do zadaného data zbývá:");
                    System.out.println("Roky: " + period.getYears());
                    System.out.println("Měsíce: " + period.getMonths());
                    System.out.println("Dny: " + period.getDays());
                    System.out.println(sekundyDoCile / 3600 + " hodin, " + (sekundyDoCile % 3600) / 60 + " minut a " + sekundyDoCile % 60 + " sekund");
                }

                case 5 -> start = false;

                default -> System.out.println("Tahle možnost není ve výběru");
            }



        }

    }
}

