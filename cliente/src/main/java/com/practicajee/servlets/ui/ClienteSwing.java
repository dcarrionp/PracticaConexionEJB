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
        setSize(500, 450); // Increased height to fit the new field
        setLocationRelativeTo(null); // Centra la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10)); // Espaciado entre componentes

        // Panel para los campos de entrada
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2, 10, 10)); // Increased grid to 6 rows
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Márgenes

        // Etiquetas y campos de texto
        panel.add(new JLabel("Cédula:", JLabel.RIGHT));
        cedulaField = new JTextField();
        panel.add(cedulaField);

        panel.add(new JLabel("Nombre:", JLabel.RIGHT));
        nombreField = new JTextField();
        panel.add(nombreField);

        panel.add(new JLabel("Apellido:", JLabel.RIGHT));
        apellidoField = new JTextField();
        panel.add(apellidoField);

        // Botones de registro y obtener
        JButton registrarButton = new JButton("Registrar");
        registrarButton.setBackground(new Color(34, 139, 34)); // Color verde
        registrarButton.setForeground(Color.WHITE);
        registrarButton.setFocusPainted(false);
        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarPersona();
            }
        });
        panel.add(registrarButton);

        JButton obtenerButton = new JButton("Obtener Personas");
        obtenerButton.setBackground(new Color(30, 144, 255)); // Color azul
        obtenerButton.setForeground(Color.WHITE);
        obtenerButton.setFocusPainted(false);
        obtenerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obtenerPersonas();
            }
        });
        panel.add(obtenerButton);

        add(panel, BorderLayout.NORTH);

        // Área de texto para mostrar personas
        personasArea = new JTextArea();
        personasArea.setEditable(false);
        personasArea.setFont(new Font("Cambria Math", Font.PLAIN, 15));
        personasArea.setLineWrap(true);
        personasArea.setWrapStyleWord(true);
        personasArea.setBorder(BorderFactory.createLineBorder(Color.CYAN, 1));
        JScrollPane scrollPane = new JScrollPane(personasArea);
        scrollPane.setPreferredSize(new Dimension(450, 150));
        add(scrollPane, BorderLayout.CENTER);

        // Configuración de la apariencia de la ventana
        getContentPane().setBackground(Color.WHITE);
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
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error: La edad debe ser un número.");
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
