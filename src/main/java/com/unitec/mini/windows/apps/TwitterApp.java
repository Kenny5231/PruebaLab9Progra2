/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package com.unitec.mini.windows.apps;

import com.unitec.mini.windows.logic.TweetManager;
import com.unitec.mini.windows.logic.TweetPost;
import com.unitec.mini.windows.ui.TimeLineEditorKit;
import java.io.File;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author leonel
 */
public class TwitterApp extends javax.swing.JInternalFrame implements AppInterface {

    private String currentUser = "admin";
    TweetManager tweetManager;

    /**
     * Creates new form Twitter
     */
    public TwitterApp() {
        initComponents();
        setComponents();
        setVisible(true);
    }

    public void setComponents() {
        ImageIcon appIcon = new ImageIcon(getClass().getResource("/images/icon_twitter_20.png"));
        this.setFrameIcon(appIcon);
        textPane.setContentType("text/html");
        timeLinePane.setContentType("text/html");

        timeLinePane.setEditorKit(new TimeLineEditorKit());
        timeLinePane.addHyperlinkListener(new HyperlinkListener() {
            @Override
            public void hyperlinkUpdate(HyperlinkEvent e) {
                if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                    handleLinkClick(e.getDescription());
                }
            }
        });

        timeLinePane.setText("");

        
        String[] emojis = {"😊", "❤️", "🎉", "🌟", "👍"};
        JList<String> emojiList = new JList<>(emojis);

        // Try to implement emoji picker -
        emojiList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selectedEmoji = emojiList.getSelectedValue();
                System.out.println("Selected Emoji: " + selectedEmoji);
            }
        });

        JScrollPane scrollPane = new JScrollPane(emojiList);
        tweetManager = new TweetManager(currentUser);

        // Add a listener on closing window, save all tweets to file...
        this.addInternalFrameListener(new InternalFrameAdapter() {
            public void internalFrameClosing(InternalFrameEvent e) {
                System.out.println("internalFrameClosing....");
                try {
                    tweetManager.saveTweetPostsToFile();
                } catch (Exception exception) {
                    System.out.println("Error saving tweet file.");
                }

                super.internalFrameClosing(e);
            }

            @Override
            public void internalFrameClosed(InternalFrameEvent e) {
                System.out.println("internalFrameClosed....");
                super.internalFrameClosed(e);
            }
        });
        
        loadTweets();
    }

    public void loadTweets() {
        Date startDate = new Date(122, 0, 1);
        Date endDate = new Date();
        List<TweetPost> loadedPosts = tweetManager.loadTweetPostsByDateAndUser(startDate, endDate, currentUser);
        String content = "";

        for (TweetPost loadedPost : loadedPosts) {
            System.out.println("---");
            content += loadedPost.toString();
        }
        System.out.println("Load content");
        //System.out.println(content);
        timeLinePane.setText(content);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane_timeLine = new javax.swing.JScrollPane();
        timeLinePane = new javax.swing.JTextPane();
        jPanel_AddTweet = new javax.swing.JPanel();
        jButton_AddImage = new javax.swing.JButton();
        jButton_Post = new javax.swing.JButton();
        jButton_AddEmoji = new javax.swing.JButton();
        jScrollPane_AddTweet = new javax.swing.JScrollPane();
        textPane = new javax.swing.JTextPane();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Twitter");

        jScrollPane_timeLine.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        timeLinePane.setEditable(false);
        timeLinePane.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane_timeLine.setViewportView(timeLinePane);

        jButton_AddImage.setText("I");
        jButton_AddImage.setToolTipText("Media");
        jButton_AddImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_AddImageActionPerformed(evt);
            }
        });

        jButton_Post.setText("Post");
        jButton_Post.setEnabled(false);
        jButton_Post.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_PostActionPerformed(evt);
            }
        });

        jButton_AddEmoji.setText("E");

        textPane.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textPaneKeyPressed(evt);
            }
        });
        jScrollPane_AddTweet.setViewportView(textPane);

        javax.swing.GroupLayout jPanel_AddTweetLayout = new javax.swing.GroupLayout(jPanel_AddTweet);
        jPanel_AddTweet.setLayout(jPanel_AddTweetLayout);
        jPanel_AddTweetLayout.setHorizontalGroup(
            jPanel_AddTweetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_AddTweetLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_AddTweetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_AddTweetLayout.createSequentialGroup()
                        .addComponent(jScrollPane_AddTweet, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_Post))
                    .addGroup(jPanel_AddTweetLayout.createSequentialGroup()
                        .addComponent(jButton_AddImage)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_AddEmoji)))
                .addContainerGap())
        );
        jPanel_AddTweetLayout.setVerticalGroup(
            jPanel_AddTweetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_AddTweetLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_AddTweetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_AddTweetLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jButton_Post)
                        .addGap(77, 77, 77))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_AddTweetLayout.createSequentialGroup()
                        .addComponent(jScrollPane_AddTweet, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel_AddTweetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_AddImage)
                    .addComponent(jButton_AddEmoji))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(137, 137, 137)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel_AddTweet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane_timeLine, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(99, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jPanel_AddTweet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane_timeLine, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jScrollPane_timeLine.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_AddImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_AddImageActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        FileFilter filter = new FileNameExtensionFilter("Image files", "jpg", "jpeg", "png", "gif");
        fileChooser.setFileFilter(filter);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File imageFile = fileChooser.getSelectedFile();
            displayImage(imageFile);
        }
    }//GEN-LAST:event_jButton_AddImageActionPerformed

    private void jButton_PostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_PostActionPerformed

        String content = processTextPaneContent(parseContent(textPane.getText()));
        TweetPost post = new TweetPost(currentUser, content);
        String textStringFormatted = post.toString();

        String timeStringProcessed = processTextPaneContent(parseContent(timeLinePane.getText()));
        if (!timeStringProcessed.isEmpty()) {
            textStringFormatted += timeStringProcessed;
        }

        timeLinePane.setContentType("text/html");
        timeLinePane.setText(textStringFormatted);
        textPane.setText("");

        jScrollPane_timeLine.getVerticalScrollBar().setValue(0);
        timeLinePane.revalidate();
        timeLinePane.repaint();
        tweetManager.addTweetPost(post);
        jButton_Post.setEnabled(false);
    }//GEN-LAST:event_jButton_PostActionPerformed

    private void textPaneKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textPaneKeyPressed
        jButton_Post.setEnabled(true);
    }//GEN-LAST:event_textPaneKeyPressed

    public static String processTextPaneContent(String html) {
        Document doc = Jsoup.parse(html);
        Element body = doc.body();
        Elements emptyPTags = body.select("p:empty");
        emptyPTags.remove();

        String processedContent = "";
        for (Element child : body.children()) {
            if (!child.tagName().equalsIgnoreCase("p") || !child.text().trim().isEmpty()) {
                processedContent += child.outerHtml();
            }
        }

        return processedContent;
    }

    private String parseContent(String content) {
        content = content.replaceAll("#(\\w+)", "<a href=\"hashtag\">$1</a>");
        return content.replaceAll("@(\\w+)", "<a href=\"mention\">$1</a>");
    }

    private void handleLinkClick(String link) {
        if ("hashtag".equals(link)) {
            String hashtag = extractHashtag(link);
            System.out.println("Clicked on hashtag: " + hashtag);
        }

        if ("mention".equals(link)) {
            String mention = extractMention(link);
            System.out.println("Clicked on mention: " + mention);
        }
    }

    private String extractHashtag(String link) {
        String regex = "<a\\s+href=\"hashtag\">(.*?)</a>";
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(regex);
        java.util.regex.Matcher matcher = pattern.matcher(link);

        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return link;
        }
    }

    private String extractMention(String link) {
        String regex = "<a\\s+href=\"mention\">(.*?)</a>";
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(regex);
        java.util.regex.Matcher matcher = pattern.matcher(link);

        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return link;
        }
    }

    private void displayImage(File file) {
        if (file != null) {
            String imagePath = file.getAbsolutePath();
            String currentContent = textPane.getText();
            String updatedContent = updateHtmlWithImage(currentContent, imagePath);
            textPane.setText(updatedContent);
        }
    }

    private String updateHtmlWithImage(String currentHtml, String imagePath) {
        currentHtml = currentHtml.replaceAll("<img[^>]*>", "");
        return currentHtml.replace("</body>", String.format("<br/><img src='file:%s' width='100' height='100'/></body>", imagePath));
    }

    @Override
    public void closeFrame() {
        try {
            this.setClosed(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void internalFrameClosing(InternalFrameEvent e) {
        System.out.println("Close Frame");
        //return;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_AddEmoji;
    private javax.swing.JButton jButton_AddImage;
    private javax.swing.JButton jButton_Post;
    private javax.swing.JPanel jPanel_AddTweet;
    private javax.swing.JScrollPane jScrollPane_AddTweet;
    private javax.swing.JScrollPane jScrollPane_timeLine;
    private javax.swing.JTextPane textPane;
    private javax.swing.JTextPane timeLinePane;
    // End of variables declaration//GEN-END:variables
}
