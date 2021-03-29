/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.OperacionesClienteTarjeta;
import java.util.Scanner;

/**
 *
 * @author velae
 */
public class main {

    public static void main(String[] args) {
        boolean next = true;
        OperacionesClienteTarjeta obj=new OperacionesClienteTarjeta();
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("----- MENU -----");
            System.out.println("1. Crear Archivo");
            System.out.println("2. Registrar Clientes");
            System.out.println("3. Guardar archivo");
            System.out.println("4. Mostrar Datos");
            System.out.println("5.Bloquear tarjeta");
            System.out.println("6. Depositar");
            System.out.println("7. Retirar");
            System.out.println("8. Salir");
            System.out.println("----------------");
            int opc = sc.nextInt();

            switch (opc) {
                case 1:
                    obj.crearArchivo();
                    break;
                case 2:
                    String res="S";;
                    while(res.equalsIgnoreCase("S")){
                        obj.crearClienteTarjeta();
                        System.out.println("Deseas seguir registrando clientes? S/N");
                        sc.nextLine();
                        res=sc.nextLine();
                    }
                    break;
                case 3:
                    obj.guardarObjetos();
                    break;
                case 4:
                    obj.leerCliente();
                    break;
                case 5:
                    obj.leerCliente();
                    System.out.println("Digite el numero de cedula");
                    int c=sc.nextInt();
                    obj.cambiarEstado(c);
                    obj.guardarObjetos();
                    break;
                case 6:
                    obj.leerCliente();
                    System.out.println("Digite el numero de cedula");
                    int ced=sc.nextInt();
                    System.out.println("Digite el monto que desea depositar");
                    double m=sc.nextDouble();                    
                    obj.depositar(ced, m);
                    obj.guardarObjetos();
                    break;
                case 7:
                    obj.leerCliente();
                     System.out.println("Digite el numero de cedula");
                    int ce=sc.nextInt();
                    System.out.println("Digite el monto que desea retirar");
                    double mn=sc.nextDouble();                    
                    obj.retirar(ce, mn);
                    obj.guardarObjetos();
                    break;
                default:
                    next = false;
                    break;
            }

        } while (next);

    }
}
