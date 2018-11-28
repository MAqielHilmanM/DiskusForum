/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home.hot_thread;

import java.awt.Component;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author maqielhm
 */
public class HotThreadRows extends javax.swing.JPanel implements ListCellRenderer{

    /**
     * Creates new form HotThreadRows
     */
    public HotThreadRows() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblHotThreadRowImage = new javax.swing.JLabel();
        lblHotThreadRowName = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblHotThreadRowCommunity = new javax.swing.JLabel();
        lblHotThreadRowTitle = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taHotThreadRowBody = new javax.swing.JTextArea();
        lblHotThreadDate = new javax.swing.JLabel();

        setName(""); // NOI18N

        lblHotThreadRowImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/images/ic_account_black_superSmall.png"))); // NOI18N

        lblHotThreadRowName.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        lblHotThreadRowName.setText("John Doe");

        jLabel3.setText("has posted something at");

        lblHotThreadRowCommunity.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        lblHotThreadRowCommunity.setText("Lorem Ipsum");

        lblHotThreadRowTitle.setFont(new java.awt.Font("DejaVu Sans", 1, 24)); // NOI18N
        lblHotThreadRowTitle.setText("Lorem Ipsum Sim Dolor Amet");

        taHotThreadRowBody.setEditable(false);
        taHotThreadRowBody.setColumns(20);
        taHotThreadRowBody.setLineWrap(true);
        taHotThreadRowBody.setRows(5);
        taHotThreadRowBody.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. \nNam ac tristique nulla. Proin viverra elit quis est \neuismod malesuada. Cras in egestas arcu. . . ");
        taHotThreadRowBody.setWrapStyleWord(true);
        taHotThreadRowBody.setAutoscrolls(false);
        taHotThreadRowBody.setBorder(null);
        taHotThreadRowBody.setOpaque(false);
        jScrollPane1.setViewportView(taHotThreadRowBody);

        lblHotThreadDate.setText("Thursday, 11 August 2018");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblHotThreadRowImage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblHotThreadRowTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblHotThreadRowName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblHotThreadRowCommunity))
                    .addComponent(jScrollPane1))
                .addContainerGap(58, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblHotThreadDate)
                .addGap(35, 35, 35))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblHotThreadRowImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblHotThreadRowName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblHotThreadRowCommunity, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblHotThreadRowTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblHotThreadDate)
                .addGap(13, 13, 13))
        );
    }// </editor-fold>//GEN-END:initComponents

//    @Override
//    public Component getListCellRendererComponent(JList<? extends HotThreadModel> list, HotThreadModel value, int index, boolean isSelected, boolean cellHasFocus) {
//        lblHotThreadRowTitle.setText(value.getTitle());
//        lblHotThreadRowName.setText(value.getName());
//        lblHotThreadRowCommunity.setText(value.getCommunity_name());
//        taHotThreadRowBody.setText(value.getPreview());
//        lblHotThreadDate.setText(value.getDate());
//        
//        return this;
//    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblHotThreadDate;
    private javax.swing.JLabel lblHotThreadRowCommunity;
    private javax.swing.JLabel lblHotThreadRowImage;
    private javax.swing.JLabel lblHotThreadRowName;
    private javax.swing.JLabel lblHotThreadRowTitle;
    private javax.swing.JTextArea taHotThreadRowBody;
    // End of variables declaration//GEN-END:variables

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        return this;
    }
}