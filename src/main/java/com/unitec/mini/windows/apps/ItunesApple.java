/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.unitec.mini.windows.apps;
import javax.swing.*;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import jaco.mp3.player.MP3Player;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;

public class ItunesApple extends javax.swing.JFrame {

    boolean playing = false;
    String dataPath = "data.flw";
    String musicPath = "";
    long songSeek = 0;
    String currentSongName = "";
    MP3Player songPlayer;
    Timer timer;
    javazoom.jl.player.Player player;
    File simpan;
    public ItunesApple() {
        initComponents();

        try {
            initializeDataFile();
            loadSongs();
            playLastSong();
        } catch (Exception e) {
            e.printStackTrace();
        }
        jSliderMusic.setValue(0);
        this.setLocation(50, 50);
        timer = new Timer(1000, e -> updateSlider());
        timer.setInitialDelay(0);
        timer.start();
        jSliderMusic.setValue(0);
        
    }
    private void initializeDataFile() throws Exception {
        File data = new File("data.flw");

        removeAllSongs();
        if (!data.exists()) {
            data.createNewFile();
            initializeDefaultData(data);
        } else {
            loadDataFromDisk(data);
        }
    }
    private void initializeDefaultData(File data) throws Exception {
        RandomAccessFile rfdata = new RandomAccessFile(data, "rw");
        rfdata.writeUTF("");
        rfdata.writeLong(0);
        rfdata.writeUTF("");
        rfdata.close();

        songTitleLbl.setText("NOT PLAYING");
        setAllBtns(false);
    }

    private void loadDataFromDisk(File data) throws Exception {
        RandomAccessFile rfdata = new RandomAccessFile(data, "rw");
        rfdata.seek(0);
        musicPath = rfdata.readUTF();
        songSeek = rfdata.readLong();
        currentSongName = rfdata.readUTF();
        loadSongs();
        playLastSong();
        rfdata.close();
    }


    private void updateData(String musicPath, long songSeek, String songName) {
        try {
            RandomAccessFile rfdata = new RandomAccessFile(dataPath, "rw");
            rfdata.writeUTF(musicPath);
            rfdata.writeLong(songSeek);
            rfdata.writeUTF(songName);
            rfdata.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private ArrayList<String> getSongNamesFromPath(String musicPath) {
        if (musicPath.equals("")) {
            return new ArrayList<String>();
        }

        File folder = new File(musicPath);
        ArrayList<String> songs = new ArrayList<>();
        for (File f : folder.listFiles()) {
            if (f.getName().endsWith(".mp3")) {
                songs.add(f.getName().replace(".mp3", ""));
            }
        }

        return songs;
    }

    private void setAllBtns(boolean enabled) {
        playBtn.setEnabled(enabled);
        backBtn.setEnabled(enabled);
        forwardBtn.setEnabled(enabled);
    }

    private void playLastSong() {
        if (currentSongName.equals("")) {
            songTitleLbl.setText("NOT PLAYING");
            setAllBtns(false);
        } else {
            boolean found = false;
            for (int i = 0; i < panelList.getModel().getSize(); i++) {
                String n = panelList.getModel().getElementAt(i);
                System.out.println(n);
                if (n.equals(currentSongName)) {
                    String path = musicPath + File.separator + currentSongName + ".mp3";
                    songPlayer = new MP3Player(new File(path));
                    songTitleLbl.setText(currentSongName);
                    playing = false;
                    playBtn.setText("PLAY");
                    setAllBtns(true);
                    found = true;
                    continue;
                }

                if (found) {
                    String name = panelList.getModel().getElementAt(i);
                    String path = musicPath + File.separator + name + ".mp3";
                    songPlayer.addToPlayList(new File(path));

                }
            }
        }
    }

    private void loadSongs() {
        System.out.println("Cargando todas las canciones...");
        ArrayList<String> songs = getSongNamesFromPath(musicPath);

        panelList.removeAll();
        DefaultListModel<String> model = new DefaultListModel<>();
        for (String song : songs) {
            model.addElement(song);
        }
        panelList.setModel(model);

        setAllBtns(false);
        songTitleLbl.setText("NOT PLAYING");

    }

    private void removeAllSongs() {
        DefaultListModel<String> model = new DefaultListModel<>();
        panelList.setModel(model);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Soporte = new javax.swing.JPanel();
        backBtn = new javax.swing.JButton();
        playBtn = new javax.swing.JButton();
        forwardBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        panelList = new javax.swing.JList<>();
        jSliderMusic = new javax.swing.JSlider();
        segundos = new javax.swing.JLabel();
        CantSegundos = new javax.swing.JLabel();
        songTitleLbl = new javax.swing.JLabel();
        BtnStop = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        Btnseleccionado = new javax.swing.JButton();
        Añadir = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        backBtn.setText("<<");
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });

        playBtn.setText("Pause");
        playBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playBtnActionPerformed(evt);
            }
        });

        forwardBtn.setText(">>");
        forwardBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                forwardBtnActionPerformed(evt);
            }
        });

        panelList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                panelListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(panelList);

        segundos.setText("00:00");

        CantSegundos.setText("00:00");

        songTitleLbl.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        songTitleLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        songTitleLbl.setText("SONG TITLE");

        BtnStop.setText("[Stop]");
        BtnStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnStopActionPerformed(evt);
            }
        });

        jButton2.setText("--");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setText("++");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout SoporteLayout = new javax.swing.GroupLayout(Soporte);
        Soporte.setLayout(SoporteLayout);
        SoporteLayout.setHorizontalGroup(
            SoporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SoporteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(backBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(playBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BtnStop)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(forwardBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 13, Short.MAX_VALUE))
            .addGroup(SoporteLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(songTitleLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(SoporteLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(segundos)
                .addGap(18, 18, 18)
                .addComponent(jSliderMusic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(CantSegundos)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SoporteLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56))
        );
        SoporteLayout.setVerticalGroup(
            SoporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SoporteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(songTitleLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(SoporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSliderMusic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(segundos)
                    .addComponent(CantSegundos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(SoporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backBtn)
                    .addComponent(playBtn)
                    .addComponent(BtnStop)
                    .addComponent(forwardBtn)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        Btnseleccionado.setText("Select");
        Btnseleccionado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnseleccionadoActionPerformed(evt);
            }
        });

        Añadir.setText("Add");
        Añadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AñadirActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Georgia", 1, 24)); // NOI18N
        jLabel1.setText("Reproductor de Musica");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(148, 148, 148))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(225, 225, 225)
                        .addComponent(Btnseleccionado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Añadir))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(Soporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(93, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(Soporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Btnseleccionado)
                    .addComponent(Añadir))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void playBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playBtnActionPerformed
 if (songPlayer != null) {
            if (songPlayer.isStopped() || songPlayer.isPaused()) {
                playing = true;
                playBtn.setText("PAUSE");
                if (!songPlayer.isStopped()) {
                    songPlayer.play();
                } else {
                    playSong(currentSongName);
                }
                jSliderMusic.setValue(0);
            } else {
                playing = false;
                playBtn.setText("PLAY");
                songPlayer.pause();
                jSliderMusic.setValue(0);
            }
        }
    }//GEN-LAST:event_playBtnActionPerformed

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        // TODO add your handling code here:
        if (songPlayer != null) {
            songPlayer.skipBackward();

            for (int i = 0; i < panelList.getModel().getSize(); i++) {
                String n = panelList.getModel().getElementAt(i);

                if (n.equals(currentSongName)) {
                    if (i - 1 == -1) {
                        currentSongName = panelList.getModel().getElementAt(0);
                    } else {
                        currentSongName = panelList.getModel().getElementAt(i - 1);

                    }
                    songTitleLbl.setText(currentSongName);
                    updateData(musicPath, songSeek, currentSongName);
                    playSong(currentSongName);
                    break;
                }
            }
        }
    }//GEN-LAST:event_backBtnActionPerformed

    private void playSong(String songName) {
         String path = musicPath + File.separator + songName + ".mp3";

        if (songPlayer != null) {
            songPlayer.stop();
        }
        songPlayer = new MP3Player(new File(path));
        songPlayer.play();
        playing = true;
        playBtn.setText("PAUSE");
    }
    private void AñadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AñadirActionPerformed
       
        if (songPlayer != null) {
            songPlayer.stop();
        }

        try {
            JFileChooser chooser = new JFileChooser(System.getProperty("user.home"));
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            chooser.showOpenDialog(this);

            File selectedFolder = chooser.getSelectedFile();

            if (selectedFolder == null) {
                return;
            }

            musicPath = selectedFolder.getCanonicalPath();
            songSeek = 0;
            currentSongName = "";
            updateData(musicPath, songSeek, currentSongName);
            loadSongs();
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    }//GEN-LAST:event_AñadirActionPerformed
    
    
  void updateSlider() {
    if (songPlayer != null) {
        int currentPosition = (int) (songPlayer.hashCode()/ 1000); 
        jSliderMusic.setValue(currentPosition);
    }
}

//private void jSliderVolumenStateChanged(javax.swing.event.ChangeEvent evt) {
//    int volumeValue = jSliderVolumen.getValue();
//    if (songPlayer != null) {
//        float volume = volumeValue / 100.0f; 
//    }
//}

    public void updateList() {
    ArrayList<String> updateList = getSongNamesFromPath(musicPath);
    DefaultListModel<String> model = new DefaultListModel<>();

    for (int i = 0; i < updateList.size(); i++) {
        int j = i + 1; 
        model.add(i, j + " | " + updateList.get(i));
    }

    panelList.setModel(model);
    }


    private void forwardBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_forwardBtnActionPerformed
        // TODO add your handling code here:
        if (songPlayer != null) {
            songPlayer.skipForward();

            for (int i = 0; i < panelList.getModel().getSize(); i++) {
                String n = panelList.getModel().getElementAt(i);

                if (n.equals(currentSongName)) {
                    if (i + 1 == panelList.getModel().getSize()) {
                        currentSongName = panelList.getModel().getElementAt(0);
                    } else {
                        currentSongName = panelList.getModel().getElementAt(i + 1);

                    }
                    songTitleLbl.setText(currentSongName);
                    updateData(musicPath, songSeek, currentSongName);

                    playSong(currentSongName);
                    break;
                }
            }
        }
    }//GEN-LAST:event_forwardBtnActionPerformed

    private void BtnseleccionadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnseleccionadoActionPerformed
      if (songPlayer != null) {
            songPlayer.stop();
        }

        if (panelList.getSelectedValue() == null) {
            JOptionPane.showMessageDialog(this, "Seleccione primero una cancion para reproducir.");
            return;
        }
        currentSongName = panelList.getSelectedValue();

        updateData(musicPath, songSeek, currentSongName);

        songTitleLbl.setText(currentSongName);
        File songFile = new File(musicPath + File.separator + currentSongName + ".mp3");
        songPlayer = new MP3Player(songFile);
        songPlayer.play();
        playing = true;
        playBtn.setText("PAUSE");
        setAllBtns(true);

        updateData(musicPath, songSeek, currentSongName);
    }//GEN-LAST:event_BtnseleccionadoActionPerformed

    private void panelListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_panelListValueChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_panelListValueChanged

    private void BtnStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnStopActionPerformed
        // TODO add your handling code here:
    if (songPlayer != null) {
        playing = false;
        playBtn.setText("PLAY");
        songPlayer.stop();
        jSliderMusic.setValue(0);
    }
    }//GEN-LAST:event_BtnStopActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        volumeUp(0.1);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        volumeDown(0.1);
    }//GEN-LAST:event_jButton2ActionPerformed
  
    private void volumeUp(Double valueToPlusMinus) {
    volumeControl(valueToPlusMinus);
}

private void volumeDown(Double valueToPlusMinus) {
    volumeControl(-valueToPlusMinus);
}

private void setVolume(Double volumeValue) {
    volumeControl(volumeValue);
}

private void volumeControl(Double valueToPlusMinus) {
    Mixer.Info[] mixers = AudioSystem.getMixerInfo();
    for (Mixer.Info mixerInfo : mixers) {
        Mixer mixer = AudioSystem.getMixer(mixerInfo);
        Line.Info[] lineInfos = mixer.getTargetLineInfo();
        for (Line.Info lineInfo : lineInfos) {
            Line line = null;
            boolean opened = true;
            try {
                line = mixer.getLine(lineInfo);
                opened = line.isOpen() || line instanceof Clip;
                if (!opened) {
                    line.open();
                }
                FloatControl volControl = (FloatControl) line.getControl(FloatControl.Type.VOLUME);
                setVolume(volControl, valueToPlusMinus);

            } catch (LineUnavailableException lineException) {
            } catch (IllegalArgumentException illException) {
            } finally {
                if (line != null && !opened) {
                    line.close();
                }
            }
        }
    }
}

private void setVolume(FloatControl volControl, Double valueToPlusMinus) {
    float currentVolume = volControl.getValue();
    Double volumeToCut = valueToPlusMinus;
    float changedCalc = (float) (currentVolume + (double) volumeToCut);
    volControl.setValue(changedCalc);
}
    
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ItunesApple.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            new ItunesApple().setVisible(true);
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Añadir;
    private javax.swing.JButton BtnStop;
    private javax.swing.JButton Btnseleccionado;
    private javax.swing.JLabel CantSegundos;
    private javax.swing.JPanel Soporte;
    private javax.swing.JButton backBtn;
    private javax.swing.JButton forwardBtn;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSlider jSliderMusic;
    private javax.swing.JList<String> panelList;
    private javax.swing.JButton playBtn;
    private javax.swing.JLabel segundos;
    private javax.swing.JLabel songTitleLbl;
    // End of variables declaration//GEN-END:variables
}
