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
    private JTextArea personasArea;
    private PersonaService personaService;

    public ClienteSwing() {
        personaService = new PersonaService();

        setTitle("Registro de Personas");
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
                registrarPersona();
            }
        });
        panel.add(registrarButton);

        JButton obtenerButton = new JButton("Obtener Personas");
        obtenerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obtenerPersonas();
            }
        });
        panel.add(obtenerButton);

        add(panel, BorderLayout.NORTH);

        personasArea = new JTextArea();
        add(new JScrollPane(personasArea), BorderLayout.CENTER);
    }

    private void registrarPersona() {
        try {
            String cedula = cedulaField.getText();
            String nombre = nombreField.getText();
            String apellido = apellidoField.getText();

            Persona persona = new Persona();
            persona.setDni(cedula);
            persona.setNombre(nombre);
            persona.setApellido(apellido);

            String mensaje = personaService.registrarPersona(persona);
            JOptionPane.showMessageDialog(this, mensaje);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void obtenerPersonas() {
        try {
            List<Persona> personas = personaService.obtenerPersonas();
            personasArea.setText("");
            for (Persona persona : personas) {
                personasArea.append(persona.toString() + "\n");
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