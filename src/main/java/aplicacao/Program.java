package aplicacao;

import model.entidade.Reserva;
import model.exceptions.DomainException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try{
        System.out.print("nuemro do quarto: ");
        int numero = ler.nextInt();
        System.out.print("Data de check-in (dd/MM/yyyy): ");
        Date checkin = sdf.parse(ler.next());
        System.out.print("Data de check-out (dd/MM/yyyy): ");
        Date checkOut = sdf.parse(ler.next());
        if (!checkOut.after(checkin)){
            System.out.println("Erro na reservação data de checkout menor q checkin");
        }else {
            Reserva reserva = new Reserva(numero, checkin, checkOut);
            System.out.println("reserva: " + reserva);


            System.out.println();
            System.out.println("Entre coms os dados para atualizar a reserva! ");
            System.out.print("Data de check-in (dd/MM/yyyy): ");
            checkin = sdf.parse(ler.next());
            System.out.print("Data de check-out (dd/MM/yyyy): ");
            checkOut = sdf.parse(ler.next());

            reserva.atualizaData(checkin, checkOut);
            System.out.println("reserva atualizada: " + reserva);
        }

        } catch(ParseException e){
            System.out.println("data invalida");
        }catch (DomainException e){
            System.out.println("erro de reserva: " + e.getMessage());
        }catch (RuntimeException e ){
            System.out.println("erro inesperado");
        }
    }
}
