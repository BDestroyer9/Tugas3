package diskon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class KalkulatorDiskon extends JFrame {
    private JTextField txtHargaAsli, txtKodeKupon;
    private JComboBox<String> cmbPersenDiskon;
    private JSlider sliderDiskon;
    private JLabel lblHargaAkhir, lblPenghematan, lblRiwayat;
    private JButton btnHitung;
    private List<String> riwayat = new ArrayList<>();

    public KalkulatorDiskon() {
        setTitle("Kalkulator Diskon");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
    panel.setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(5, 5, 5, 5);
    JButton btnHitung = new JButton("Hitung");

        // Inisialisasi komponen
        txtHargaAsli = new JTextField(10);
        txtKodeKupon = new JTextField(10);
        cmbPersenDiskon = new JComboBox<>(new String[]{"10", "20", "30", "40", "50"}); // Persentase diskon tanpa simbol %
        
        // Tambahkan JSlider untuk memilih persentase diskon
        sliderDiskon = new JSlider(0, 100);
        sliderDiskon.addChangeListener(e -> {
        cmbPersenDiskon.setSelectedIndex(sliderDiskon.getValue());
        });
        
        // Tambahkan label dan field untuk kode kupon
        JLabel lblKodeKupon = new JLabel("Kode Kupon:");
        txtKodeKupon = new JTextField(10);
        
        lblHargaAkhir = new JLabel("Harga Akhir: Rp 0");
         // Tambahkan label untuk menampilkan riwayat
        lblPenghematan = new JLabel("Penghematan: Rp 0");
         // Tambahkan label untuk menampilkan riwayat
        lblRiwayat = new JLabel("Riwayat:");
        // Tambahkan label untuk menampilkan riwayat
       String[] persenDiskon = {"10%", "20%", "30%", "40%", "50%"};
       
        btnHitung = new JButton("Hitung");

        // Atur slider untuk mengubah JComboBox
        sliderDiskon.addChangeListener(e -> {
            cmbPersenDiskon.setSelectedItem(String.valueOf(sliderDiskon.getValue()));
        });

        // Tambahkan komponen ke panel
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Harga Asli:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0;
        panel.add(txtHargaAsli, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Diskon (%):"), gbc);
        gbc.gridx = 1; gbc.gridy = 1;
        panel.add(cmbPersenDiskon, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Diskon (Slider):"), gbc);
        gbc.gridx = 1; gbc.gridy = 2;
        panel.add(sliderDiskon, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Kode Kupon:"), gbc);
        gbc.gridx = 1; gbc.gridy = 3;
        panel.add(txtKodeKupon, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(btnHitung, gbc);

        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
        panel.add(lblHargaAkhir, gbc);

        gbc.gridx = 0; gbc.gridy = 6; gbc.gridwidth = 2;
        panel.add(lblPenghematan, gbc);

        gbc.gridx = 0; gbc.gridy = 7; gbc.gridwidth = 2;
        panel.add(lblRiwayat, gbc);

        // Tambahkan ActionListener untuk tombol Hitung
       btnHitung.addActionListener(e -> hitungDiskon());

        // Tambahkan ItemListener pada JComboBox untuk memicu perhitungan saat nilai berubah
        cmbPersenDiskon.addItemListener(e -> hitungDiskon());

            add(panel);
            setVisible(true);
}

    private void hitungDiskon() {
      try {
        double hargaAsli = Double.parseDouble(txtHargaAsli.getText());
        int persenDiskon = Integer.parseInt(cmbPersenDiskon.getSelectedItem().toString());
        double diskon = hargaAsli * persenDiskon / 100;
        double hargaAkhir = hargaAsli - diskon;
        double penghematan = diskon;

        // Tambahkan logika untuk memproses kode kupon (misalnya, memberikan diskon tambahan)
        lblHargaAkhir.setText("Harga Akhir: Rp " + hargaAkhir);
        lblPenghematan.setText("Penghematan: Rp " + penghematan);

        // Tambahkan riwayat perhitungan
        String riwayatBaru = "Harga Asli: Rp " + hargaAsli + ", Diskon: " + persenDiskon + "%, Harga Akhir: Rp " + hargaAkhir;
        riwayat.add(riwayatBaru);
        lblRiwayat.setText("<html>" + String.join("<br>", riwayat) + "</html>");
    } 
        catch (NumberFormatException ex) {
        }
    }
        public static void main(String[] args){ 
            new KalkulatorDiskon();}
}