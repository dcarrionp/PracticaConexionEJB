package com.practicajee.servlets.ui;

import com.practicajee.servlets.model.Persona;
import com.practicajee.servlets.service.PersonaService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ClienteSwing extends JFrame {
    private JTextField cedulaField;
    private JTextField nombreField;
    private JTextField apellidoField;
    private JTextArea estudiantesArea;
    private PersonaService estudianteService;

    public ClienteSwing() {
        estudianteService = new PersonaService();

        setTitle("Registro de Estudiantes");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(new JLabel("Cédula:"));
        cedulaField = new JTextField();
        panel.add(cedulaField);

        panel.add(new JLabel("Nombre:"));
        nombreField = new JTextField();
        panel.add(nombreField);

        panel.add(new JLabel("Apellido:"));
        apellidoField = new JTextField();
        panel.add(apellidoField);

        JButton registrarButton = new JButton("Registrar");
        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarEstudiante();
            }
        });
        panel.add(registrarButton);

        JButton obtenerButton = new JButton("Obtener Estudiantes");
        obtenerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obtenerEstudiantes();
            }
        });
        panel.add(obtenerButton);

        add(panel, BorderLayout.NORTH);

        estudiantesArea = new JTextArea();
        add(new JScrollPane(estudiantesArea), BorderLayout.CENTER);
    }

    private void registrarEstudiante() {
        try {
            String cedula = cedulaField.getText();
            String nombre = nombreField.getText();
            String apellido = apellidoField.getText();

            Persona estudiante = new Persona();
            estudiante.setDni(cedula);
            estudiante.setNombre(nombre);
            estudiante.setApellido(apellido);

            String mensaje = estudianteService.registrarEstudiante(estudiante);
            JOptionPane.showMessageDialog(this, mensaje);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void obtenerEstudiantes() {
        try {
            List<Persona> estudiantes = estudianteService.obtenerEstudiantes();
            estudiantesArea.setText("");
            for (Persona estudiante : estudiantes) {
                estudiantesArea.append(estudiante.toString() + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ClienteSwing().setVisible(true);
            }
        });
    }
}