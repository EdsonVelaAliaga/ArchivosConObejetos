/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Cliente;
import modelo.TarjetaDebito;

/**
 *
 * @author velae
 */
public class OperacionesClienteTarjeta {

    List<Cliente> listaCliente;
    Scanner sc = new Scanner(System.in);

    public OperacionesClienteTarjeta() {
        this.listaCliente = new ArrayList<>();
    }

    public void crearClienteTarjeta() {
        Cliente cliente = new Cliente();
        TarjetaDebito tarjeta = new TarjetaDebito();
        System.out.println("----- CLIENTE -----");
        System.out.println("Digite el nombre:");
        cliente.setNombre(sc.nextLine());
        System.out.println("Digite el apellido paterno: ");
        cliente.setPaterno(sc.nextLine());
        System.out.println("Digite el apellido materno:");
        cliente.setMaterno(sc.nextLine());
        System.out.println("Digite el nro. de cedula:");
        cliente.setCedula(sc.nextInt());
        sc.nextLine();
        System.out.println("---- TARJETA -----");
        System.out.println("Digite el nro. tarjeta:");
        tarjeta.setNroTarjeta(sc.nextInt());
        System.out.println("Digite el nro cuenta:");
        tarjeta.setNroCuenta(sc.nextInt());
        System.out.println("Digite el saldo de la tarjeta");
        tarjeta.setSaldo(sc.nextDouble());
        tarjeta.setEstado("ACTIVO");
        cliente.setTarjeta(tarjeta);
        this.listaCliente.add(cliente);
        sc.nextLine();
        sc.nextLine();
        System.out.println("----- REGISTRO COMPLETO -----");
    }

    public void mostrarCliente() {
        if (this.listaCliente != null) {
            System.out.println("---- CLIENTES ACTIVOS ----");
            for (Cliente c : this.listaCliente) {
                c.mostrarCliente();
            }
        }
    }

    public void crearArchivo() {
        Path path = Paths.get("C:\\Users\\velae\\Documents\\NetBeansProjects\\ProgIIIArchivosNIO\\archivoCliente.txt");
        try {
            if (!Files.exists(path)) {
                Files.createFile(path);
                System.out.println("El archivo se creo correctamente!!!");
            } else {
                System.out.println("El archivo ya existe...");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void cambiarEstado(int nroCedula) {
        int sw = 0;
        if (this.listaCliente != null) {
            for (Cliente c : this.listaCliente) {
                if (c.getCedula() == nroCedula) {
                    sw = 1;
                    c.getTarjeta().setEstado("INACTIVO");
                    System.out.println("Se BLOQUEO la tarjeta");
                }
            }
            if (sw == 0) {
                System.out.println("El cliente no esta registrado");
            }
        }
    }

    public void guardarObjetos() {
        String ruta = "C:\\Users\\velae\\Documents\\NetBeansProjects\\ProgIIIArchivosNIO\\archivoCliente.txt";
        try {
            FileOutputStream archivo = new FileOutputStream(ruta);
            ObjectOutputStream oos = new ObjectOutputStream(archivo);
            oos.writeObject(this.listaCliente);
            System.out.println("Guardado");
            oos.close();
            archivo.close();
        } catch (FileNotFoundException e) {
            Logger.getLogger(OperacionesClienteTarjeta.class.getName()).log(Level.SEVERE, null, e);
        } catch (IOException e) {
            Logger.getLogger(OperacionesClienteTarjeta.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void leerCliente() {
        String ruta = "C:\\Users\\velae\\Documents\\NetBeansProjects\\ProgIIIArchivosNIO\\archivoCliente.txt";
        try {
            FileInputStream archivo = new FileInputStream(ruta);
            ObjectInputStream ois = new ObjectInputStream(archivo);
            if (ois != null) {
                this.listaCliente = (List<Cliente>) ois.readObject();
                mostrarCliente();
                System.out.println("---");
            } else {
                System.out.println("El objeto es nulo");
            }
        } catch (FileNotFoundException e) {
            Logger.getLogger(OperacionesClienteTarjeta.class.getName()).log(Level.SEVERE, null, e);
        } catch (IOException e) {
            Logger.getLogger(OperacionesClienteTarjeta.class.getName()).log(Level.SEVERE, null, e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OperacionesClienteTarjeta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void depositar(int nroCedula, double monto) {
        int sw = 0;
        if (this.listaCliente != null) {
            for (Cliente c : this.listaCliente) {
                if (c.getCedula() == nroCedula) {
                    sw = 1;
                    if (c.getTarjeta().getEstado().equals("ACTIVO")) {
                        double saldo = c.getTarjeta().getSaldo();
                        saldo = saldo + monto;
                        c.getTarjeta().setSaldo(saldo);
                        System.out.println("Se realizo el deposito!!!");
                    } else {
                        System.out.println("La tarjeta esta bloqueada ");
                        System.out.println("Comuniquese con el Banco!!!");
                    }
                }
            }
            if (sw == 0) {
                System.out.println("El cliente no esta registrado");
            }
        } else {
            System.out.println("Lista NULL");
        }
    }

    public void retirar(int nroCedula, double monto) {
        int sw = 0;
        if (this.listaCliente != null) {
            for (Cliente c : this.listaCliente) {
                if (c.getCedula() == nroCedula) {
                    sw = 1;
                    if (c.getTarjeta().getEstado().equals("ACTIVO")) {
                        if (c.getTarjeta().getSaldo() > monto) {
                            double saldo = c.getTarjeta().getSaldo();
                            saldo = saldo - monto;
                            c.getTarjeta().setSaldo(saldo);
                            System.out.println("Se realizo el retiro!!!");
                        }else{
                            System.out.println("El monto a retirar es mayor a su saldo actual!!!");
                        }
                    } else {
                        System.out.println("La tarjeta esta bloqueada ");
                        System.out.println("Comuniquese con el Banco!!!");
                    }
                }
            }
            if (sw == 0) {
                System.out.println("El cliente no esta registrado");
            }
        } else {
            System.out.println("Lista NULL");
        }
    }

}
