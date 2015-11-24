package segmentacaodeimagem;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import tree.Trie;

/**
 * Classe de controle de interface. Todas as operações da interface do sistema
 * são controladas por ela
 *
 * @author Hiago Miguel e Rai Vitor.
 */
public class FormSegmentacao extends javax.swing.JFrame {

    JFileChooser fileChooser; /* Guarda as informacoes da imagem selecionada pelo usuario */
    JLabel imagem; /* Representa a imagem orignal */
    DefaultListModel tagsModel; /* Componente responsável por mostrar a lista de anotações */
    DefaultListModel tagsBancoModel; /* Componente responsável por mostrar a lista de anotações que estão no banco */
    DefaultComboBoxModel boxModel; /* Componente responsável para mostrar a lista de anotações no ComboBox */
    ListAnotacoes<Anotacao> tags; /* Guarda uma lista de anotações */
    Trie t; /* Arvore Trie que guarda a lista do ComboBox */
    Imagem img; /* Guarda as informações da imagem que está sendo mostrada */
    Regiao regiao; /*  */
    
    private static final int DEFAULT_WIDTH = 533; /*Largura do redimensionamento da imagem */
    private static final int DEFAULT_HEIGHT = 400; /*Altura do redimensionamento da imagem */
    private final double BLUR = 0.50; /* Valor inicial do blur */
    private final int RADIUS = 50; /* Valor inicial do Radius */
    private final int SIZE = 500; /* Valor inicial do Size */
    
    /**
     * Cria um novo form FormSegmentacao.
     */
    public FormSegmentacao() {
        initComponents();
        ConfigForm();
    }

    /**
     * Inicializa componentes do form.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fileDialog = new javax.swing.JDialog();
        panelPrincipal = new javax.swing.JPanel();
        panelGeral = new javax.swing.JPanel();
        panelNotes = new javax.swing.JPanel();
        buttonAdd = new javax.swing.JButton();
        buttonClear = new javax.swing.JButton();
        buttonSave = new javax.swing.JButton();
        buttonDelete = new javax.swing.JButton();
        campoTag = new javax.swing.JTextField();
        boxBusca = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        listaTags = new javax.swing.JList();
        jScrollPane4 = new javax.swing.JScrollPane();
        listaImgs = new javax.swing.JList();
        panelImg = new javax.swing.JPanel();
        panelCtrl = new javax.swing.JPanel();
        valBlur = new javax.swing.JSpinner();
        valRadius = new javax.swing.JSpinner();
        valSize = new javax.swing.JSpinner();
        labelBlur = new javax.swing.JLabel();
        labelRadius = new javax.swing.JLabel();
        labelSize = new javax.swing.JLabel();
        labelRegioes = new javax.swing.JLabel();
        buttonSegmentar = new javax.swing.JButton();
        buttonRotulos = new javax.swing.JButton();
        labelImg = new javax.swing.JLabel();
        buttonImg = new javax.swing.JButton();

        javax.swing.GroupLayout fileDialogLayout = new javax.swing.GroupLayout(fileDialog.getContentPane());
        fileDialog.getContentPane().setLayout(fileDialogLayout);
        fileDialogLayout.setHorizontalGroup(
            fileDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 638, Short.MAX_VALUE)
        );
        fileDialogLayout.setVerticalGroup(
            fileDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 399, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema de segmentação, anotação e busca de imagens");
        setMinimumSize(new java.awt.Dimension(400, 200));
        setName("frame"); // NOI18N
        setPreferredSize(new java.awt.Dimension(1335, 450));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        panelPrincipal.setLayout(new javax.swing.BoxLayout(panelPrincipal, javax.swing.BoxLayout.Y_AXIS));

        panelGeral.setPreferredSize(new java.awt.Dimension(2000, 400));

        panelNotes.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelNotes.setPreferredSize(new java.awt.Dimension(200, 400));

        buttonAdd.setText("+");
        buttonAdd.setEnabled(false);
        buttonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddActionPerformed(evt);
            }
        });

        buttonClear.setText("Limpar Seleção");
        buttonClear.setEnabled(false);
        buttonClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonClearActionPerformed(evt);
            }
        });

        buttonSave.setText("Salvar Alterações");
        buttonSave.setEnabled(false);
        buttonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSaveActionPerformed(evt);
            }
        });

        buttonDelete.setText("Excluir Tag");
        buttonDelete.setEnabled(false);
        buttonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDeleteActionPerformed(evt);
            }
        });

        boxBusca.setEditable(true);
        boxBusca.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jScrollPane2.setPreferredSize(new java.awt.Dimension(300, 132));

        listaTags.setMaximumSize(new java.awt.Dimension(150, 150));
        listaTags.setPreferredSize(new java.awt.Dimension(150, 150));
        listaTags.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listaTagsMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(listaTags);

        jScrollPane4.setPreferredSize(new java.awt.Dimension(300, 132));

        listaImgs.setPreferredSize(new java.awt.Dimension(200, 200));
        listaImgs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listaImgsMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(listaImgs);

        javax.swing.GroupLayout panelNotesLayout = new javax.swing.GroupLayout(panelNotes);
        panelNotes.setLayout(panelNotesLayout);
        panelNotesLayout.setHorizontalGroup(
            panelNotesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNotesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelNotesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(buttonSave, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
                    .addComponent(buttonDelete, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonClear, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelNotesLayout.createSequentialGroup()
                        .addComponent(campoTag)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonAdd))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelNotesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(boxBusca, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelNotesLayout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelNotesLayout.setVerticalGroup(
            panelNotesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelNotesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelNotesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelNotesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonAdd)
                    .addComponent(campoTag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(boxBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonClear, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonDelete)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(157, 157, 157))
        );

        panelImg.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelImg.setPreferredSize(new java.awt.Dimension(400, 533));

        javax.swing.GroupLayout panelImgLayout = new javax.swing.GroupLayout(panelImg);
        panelImg.setLayout(panelImgLayout);
        panelImgLayout.setHorizontalGroup(
            panelImgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 528, Short.MAX_VALUE)
        );
        panelImgLayout.setVerticalGroup(
            panelImgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panelCtrl.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelCtrl.setToolTipText("Ajustes");
        panelCtrl.setMinimumSize(new java.awt.Dimension(150, 400));
        panelCtrl.setName(""); // NOI18N
        panelCtrl.setPreferredSize(new java.awt.Dimension(180, 400));

        labelBlur.setText("BlurLevel:");

        labelRadius.setText("ColorRadius:");

        labelSize.setText("MinSize:");

        labelRegioes.setText("Total de regiões: ");

        buttonSegmentar.setText("Segmentar");
        buttonSegmentar.setEnabled(false);
        buttonSegmentar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSegmentarActionPerformed(evt);
            }
        });

        buttonRotulos.setText("Mostrar mapa de rótulos");
        buttonRotulos.setEnabled(false);
        buttonRotulos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRotulosActionPerformed(evt);
            }
        });

        labelImg.setText("NomeImg:");

        buttonImg.setText("Selecionar Imagem");
        buttonImg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonImgActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelCtrlLayout = new javax.swing.GroupLayout(panelCtrl);
        panelCtrl.setLayout(panelCtrlLayout);
        panelCtrlLayout.setHorizontalGroup(
            panelCtrlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCtrlLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelCtrlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCtrlLayout.createSequentialGroup()
                        .addGroup(panelCtrlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(buttonRotulos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buttonSegmentar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buttonImg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panelCtrlLayout.createSequentialGroup()
                                .addGroup(panelCtrlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelSize)
                                    .addComponent(labelRadius)
                                    .addComponent(labelBlur))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(panelCtrlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(valSize)
                                    .addComponent(valRadius, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(valBlur, javax.swing.GroupLayout.Alignment.LEADING))))
                        .addGap(37, 37, 37))
                    .addGroup(panelCtrlLayout.createSequentialGroup()
                        .addComponent(labelRegioes)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelCtrlLayout.createSequentialGroup()
                        .addComponent(labelImg)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        panelCtrlLayout.setVerticalGroup(
            panelCtrlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCtrlLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(panelCtrlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(valBlur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelBlur))
                .addGap(33, 33, 33)
                .addGroup(panelCtrlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(valRadius, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelRadius))
                .addGap(34, 34, 34)
                .addGroup(panelCtrlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(valSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelSize))
                .addGap(27, 27, 27)
                .addComponent(labelRegioes)
                .addGap(18, 18, 18)
                .addComponent(labelImg)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonImg)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonSegmentar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonRotulos)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelGeralLayout = new javax.swing.GroupLayout(panelGeral);
        panelGeral.setLayout(panelGeralLayout);
        panelGeralLayout.setHorizontalGroup(
            panelGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGeralLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelCtrl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(panelImg, javax.swing.GroupLayout.PREFERRED_SIZE, 534, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelNotes, javax.swing.GroupLayout.PREFERRED_SIZE, 586, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelGeralLayout.setVerticalGroup(
            panelGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelGeralLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(panelGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelImg, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                    .addComponent(panelCtrl, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelNotes, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        panelPrincipal.add(panelGeral);

        getContentPane().add(panelPrincipal, new java.awt.GridBagConstraints());

        getAccessibleContext().setAccessibleDescription("frame");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Configurações iniciais do layout.
     */
    private void ConfigForm() {
        fileChooser = new JFileChooser();
        valBlur.setModel(new SpinnerNumberModel(BLUR, 0.00, 100.00, 0.01));
        valRadius.setModel(new SpinnerNumberModel(RADIUS, 1, 100, 2));
        valSize.setModel(new SpinnerNumberModel(SIZE, 1, 1000, 20));
        img = null;
        
        tagsModel = new DefaultListModel();
        listaTags.setModel(tagsModel);
        
        tags = new ListAnotacoes<>();
        CtrlBotoes(1, false);

        tagsBancoModel = new DefaultListModel();
        initComboBox();
        boxBusca.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        ListarTags((String)boxBusca.getSelectedItem());
                    }
                });
            }
        });
    }

    /**
     * Adiciona na listaTags um novo item.
     * @param evt 
     */
    private void buttonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddActionPerformed
        String tag = campoTag.getText();

        //Se tiver o campo vazio
        if (tag.equals("")) {
            campoTag.requestFocusInWindow();
            campoTag.selectAll();
            return;
        }
        
        //Para não ter elementos repetidos na lista
        if(!tagsModel.contains(tag)){
            tag = tag.replace(" ", "_");
            regiao.AssocTagRegiao(tag, tags, img);
            tagsModel.addElement(tag);
            listaTags.setModel(tagsModel);
            CtrlBotoes(5, true);
        } else{
            JOptionPane.showMessageDialog(null, "Você não pode repetir a mesma anotação na imagem", "Anotações", ERROR_MESSAGE);
        }
        
        campoTag.requestFocusInWindow();
        campoTag.setText("");
        
        img.RestaurarImg(1);
        addImg(new ImageIcon(img.getImgSegmentada()));
    }//GEN-LAST:event_buttonAddActionPerformed
    
    /**
     * Mostra na tela a imagem com a região destacada que tem a tag selecionada pelo usuário
     * @param evt - Mouse click
     */
    private void listaTagsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaTagsMouseClicked
        if(tagsModel.size() > 0 || img == null){
            String tag = (String)listaTags.getSelectedValue();
            Busca.Selecionar(img, tag, tags);
            addImg(new ImageIcon(img.getImgSegmentada()));  
        }
    }//GEN-LAST:event_listaTagsMouseClicked
  
    /**
     * Limpa todas as seleções.
     * @param evt 
     */
    private void buttonClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonClearActionPerformed
        img.RestaurarImg(1);
        addImg(new ImageIcon(img.getImgSegmentada()));
        CtrlBotoes(4, false);
        CtrlBotoes(7, false);
    }//GEN-LAST:event_buttonClearActionPerformed

    /**
     * Salva as anotações de uma imagem. Chamado após clicar no botão 
     * 'Salvar Anotações'.
     * @param evt 
     */
    private void buttonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSaveActionPerformed
        if(tags.size() > 0){
            img.Salvar();
            tags.Salvar();
            JOptionPane.showMessageDialog(null, "Anotações salvas", "Salvo", INFORMATION_MESSAGE);
            CtrlBotoes(6, false);
            initComboBox();
            buttonClearActionPerformed(evt);
        }
    }//GEN-LAST:event_buttonSaveActionPerformed

    /**
     * Retira uma tag da lista.
     * @param evt 
     */
    private void buttonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDeleteActionPerformed
        if(tagsModel.size() > 0 || img == null){
            String tag = (String)listaTags.getSelectedValue();
            tagsModel.removeElement(tag);
            tags.Remover(tag);
            buttonClearActionPerformed(evt);
            initComboBox();
        }
    }//GEN-LAST:event_buttonDeleteActionPerformed

    /**
     * Método chamado quando usuário clica em listaTagsBanco.
     * Este método mostra na tela a imagem, selecionada pelo usuário, com as anotações.
     * @param evt - evento do mouse click
     */
    private void listaImgsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaImgsMouseClicked
        //verifica se clicou em um item ou num lugar vazio
        if(listaImgs.getSelectedValue() == null){
            return;
        }
        
        String tag = (String)listaImgs.getSelectedValue();
        
        img = Busca.BuscarImg(tag, tagsModel, tags);
        listaTags.setModel(tagsModel);
        img.segmentar(img.getBlur(), img.getRadius(), img.getSize());
        labelRegioes.setText("Total de regiões: " + img.getTotalRegioes());
        labelImg.setText("NomeImg: " + img.getPath());
        
        addImg(new ImageIcon(img.getImgSegmentada()));
        CtrlBotoes(5, true);
    }//GEN-LAST:event_listaImgsMouseClicked

    /**
     * Este método é chamado após o botão 'Selecionar Imagem' ser acionado Abre
     * um dialog para o usuário escolher uma imagem e mostra a imagem na tela.
     * Só aceita imagens jpg.
     */
    private void buttonImgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonImgActionPerformed
        int returnVal = fileChooser.showOpenDialog(fileDialog);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            //File file = new File("imgs/model.jpg");
            if (file.getName().contains("jpg")) {
                String path = file.getAbsolutePath();
                ConvertImage.scaleImage(DEFAULT_WIDTH, DEFAULT_HEIGHT, path);
                path = ConvertImage.getCaminhoDaImagem();
                ImageIcon image = new ImageIcon(path);
                addImg(image);
                img = new Imagem(path, BLUR, RADIUS, SIZE);
                labelImg.setText("NomeImg: " + path);
                CtrlBotoes(2, true);
                tagsBancoModel.clear();
                tagsModel.clear();
            } else {
                CtrlBotoes(1, false);
                labelImg.setText("Utilize somente imagens jpg");
            }
        }
    }//GEN-LAST:event_buttonImgActionPerformed

    /**
     * Este método é chamado após o botão "Mostrar mapa de rótulos" ser
     * acionado. 
     * Mostra o mapa de rótulos na tela.
     */
    private void buttonRotulosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRotulosActionPerformed
        img.GerarMapaRotulos();
        addImg(new ImageIcon(img.getImgSegmentada()));
    }//GEN-LAST:event_buttonRotulosActionPerformed

    /**
     * Este método é chamado após o botão "Segmentar" ser acionado. 
     * A função dele é pegar uma imagem escolhida pelo usuário e mostrar ela segmentada na tela.
     */
    private void buttonSegmentarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSegmentarActionPerformed
        //Pega os valores da interface
        double blur = (double) valBlur.getValue();
        int radius = (int) valRadius.getValue();
        int size = (int) valSize.getValue();

        img.segmentar(blur, radius, size);

        labelRegioes.setText("Total de regiões: " + img.getTotalRegioes());
        addImg(new ImageIcon(img.getImgSegmentada()));
        CtrlBotoes(3, true);
    }//GEN-LAST:event_buttonSegmentarActionPerformed

    /**
     * Adiciona uma imagem na interface e remove outras que estejam adicionadas.
     * @param img - Imagem a ser adicionada
     */
    private void addImg(ImageIcon image) {
        if (imagem != null) {
            panelImg.remove(imagem);
        }

        Image imgOriginal = image.getImage();
        Image newimg = imgOriginal.getScaledInstance(DEFAULT_WIDTH, DEFAULT_HEIGHT, java.awt.Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(newimg);
        imagem = new JLabel(newIcon);
        Dimension d = new Dimension(newimg.getWidth(rootPane), newimg.getHeight(rootPane));
        imagem.setMaximumSize(d);
        imagem.setSize(d);
        panelImg.add(imagem);
        revalidate();
        repaint();

        if(img == null){
            return ;
        }
        
        imagem.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
               regiao = new Regiao(e.getX(),e.getY(), imagem.getWidth());
               regiao.destacarRegiao(img);
               addImg(new ImageIcon(img.getImgSegmentada()));
               CtrlBotoes(4, true);
            }
        });
    }
    
    /**
     * Inicializa o ComboBox com as anotações que estão no banco de dados.
     */
    private void initComboBox(){
        t = Busca.BuscarTags();
        ArrayList<String> listaTag = new ArrayList<>();
        t.print(t.getRoot(), "", listaTag);
        boxModel = new DefaultComboBoxModel();
        
        for (int i = 0; i < listaTag.size(); i++) {
            boxModel.addElement(listaTag.get(i));
        }
        
        boxBusca.setModel(boxModel);
        AutoCompleteDecorator.decorate(boxBusca);
    }
    
    /**
     * Lista as tags selecionadas pelo ComboBox na segunda lista.
     * @param tag 
     */
    private void ListarTags(String tag){
        ArrayList<String> listaPaths = Busca.ListarTags(tag);
        tagsBancoModel.clear();
        for(int i = 0; i<listaPaths.size(); i++){
            tagsBancoModel.addElement(listaPaths.get(i));
        }
        
        listaImgs.setModel(tagsBancoModel);
    }

    /**
     * 1 - todos
     * 2 - segmentar
     * 3 - rotulos
     * 4 - clear e add
     * 5 - save e delete
     * 6 - save
     * 7 - clear
     * @param id Numero que informa qual grupo de botao deseja selecionar
     * @param bool true se deseja habilitar ou false se deseja desabilitar
     */
    public void CtrlBotoes(int id, Boolean bool){
        //bool = true;
        switch (id){
            case 1:
                buttonSegmentar.setEnabled(bool);
                buttonRotulos.setEnabled(bool);
                buttonAdd.setEnabled(bool);
                buttonClear.setEnabled(bool);
                buttonDelete.setEnabled(bool);
                buttonSave.setEnabled(bool);
                campoTag.setEnabled(bool);
                break;
            case 2:
                buttonSegmentar.setEnabled(bool);
                break;
            case 3:
                buttonRotulos.setEnabled(bool);
                break;
            case 4:
                campoTag.setEnabled(bool);
                buttonClear.setEnabled(bool);
                buttonAdd.setEnabled(bool);
                break;
            case 5:
                buttonSave.setEnabled(bool);
                buttonDelete.setEnabled(bool);
                buttonRotulos.setEnabled(bool);
                buttonSegmentar.setEnabled(bool);
                break;
            case 6:
                buttonSave.setEnabled(bool);
                break;
            case 7:
                buttonClear.setEnabled(bool);
                break;
        }
    }
    
    /**
     * Método que inicia o sistema.
     * @param args argumentos para iniciar o sistema
     */
    public static void main(String args[]) {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormSegmentacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormSegmentacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormSegmentacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormSegmentacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormSegmentacao().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox boxBusca;
    private javax.swing.JButton buttonAdd;
    private javax.swing.JButton buttonClear;
    private javax.swing.JButton buttonDelete;
    private javax.swing.JButton buttonImg;
    private javax.swing.JButton buttonRotulos;
    private javax.swing.JButton buttonSave;
    private javax.swing.JButton buttonSegmentar;
    private javax.swing.JTextField campoTag;
    private javax.swing.JDialog fileDialog;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel labelBlur;
    private javax.swing.JLabel labelImg;
    private javax.swing.JLabel labelRadius;
    private javax.swing.JLabel labelRegioes;
    private javax.swing.JLabel labelSize;
    private javax.swing.JList listaImgs;
    private javax.swing.JList listaTags;
    private javax.swing.JPanel panelCtrl;
    private javax.swing.JPanel panelGeral;
    private javax.swing.JPanel panelImg;
    private javax.swing.JPanel panelNotes;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JSpinner valBlur;
    private javax.swing.JSpinner valRadius;
    private javax.swing.JSpinner valSize;
    // End of variables declaration//GEN-END:variables
}
