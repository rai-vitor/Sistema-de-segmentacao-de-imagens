package segmentacaodeimagem;

import br.ufrn.imd.lp2.imagesegmentation.ImageInformation;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Vector;
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

/**
 * Classe de controle de interface. Todas as operações da interface do sistema
 * são controladas por ela
 *
 * @author Hiago Miguel & Rai Vitor.
 */
public class FormSegmentacao extends javax.swing.JFrame {

    JFileChooser fileChooser; /*Guarda as informacoes da imagem selecionada pelo usuario */
    String path; /*Caminho absoluto da imagem */
    JLabel imagem; /*Representa a imagem orignal */
    ImageInformation seg;/*Contem a imagem segmentada */
    DefaultListModel tagsModel; /* Componente responsável por mostrar a lista de anotações */
    ListAnotacoes<Anotacao> tags; /* Guarda uma lista de anotações */
    Vector vectorPesawat = new Vector();
    DefaultComboBoxModel boxModel;
    String pesawat[] = {"Camisa","Sapato","Calça","Blusa","Short"};
    private static final int DEFAULT_WIDTH = 400; /*Largura do redimensionamento da imagem */
    private static final int DEFAULT_HEIGHT = 400; /*Altura do redimensionamento da imagem */
    private final double BLUR = 0.50;
    private final int RADIUS = 50;
    private final int SIZE = 500;
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
        jScrollPane2 = new javax.swing.JScrollPane();
        listaTags = new javax.swing.JList();
        buttonClear = new javax.swing.JButton();
        buttonSave = new javax.swing.JButton();
        buttonDelete = new javax.swing.JButton();
        campoTag = new javax.swing.JTextField();
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
        panelBusca = new javax.swing.JPanel();
        boxBusca = new javax.swing.JComboBox();

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
        setPreferredSize(new java.awt.Dimension(850, 550));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        panelPrincipal.setLayout(new javax.swing.BoxLayout(panelPrincipal, javax.swing.BoxLayout.Y_AXIS));

        panelNotes.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelNotes.setPreferredSize(new java.awt.Dimension(200, 400));

        buttonAdd.setText("+");
        buttonAdd.setEnabled(false);
        buttonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddActionPerformed(evt);
            }
        });

        listaTags.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listaTagsMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(listaTags);

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

        javax.swing.GroupLayout panelNotesLayout = new javax.swing.GroupLayout(panelNotes);
        panelNotes.setLayout(panelNotesLayout);
        panelNotesLayout.setHorizontalGroup(
            panelNotesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNotesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelNotesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonClear, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelNotesLayout.createSequentialGroup()
                        .addComponent(campoTag)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonAdd))
                    .addComponent(buttonSave, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                    .addComponent(buttonDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelNotesLayout.setVerticalGroup(
            panelNotesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNotesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelNotesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonAdd)
                    .addComponent(campoTag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(buttonClear, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(69, Short.MAX_VALUE))
        );

        panelImg.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelImg.setPreferredSize(new java.awt.Dimension(400, 400));

        javax.swing.GroupLayout panelImgLayout = new javax.swing.GroupLayout(panelImg);
        panelImg.setLayout(panelImgLayout);
        panelImgLayout.setHorizontalGroup(
            panelImgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 394, Short.MAX_VALUE)
        );
        panelImgLayout.setVerticalGroup(
            panelImgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 394, Short.MAX_VALUE)
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
                        .addGroup(panelCtrlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelRegioes)
                            .addComponent(labelImg))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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
                .addGap(18, 18, 18)
                .addComponent(labelRegioes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelImg)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                .addComponent(buttonImg)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonSegmentar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonRotulos)
                .addContainerGap())
        );

        panelBusca.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        boxBusca.setEditable(true);
        boxBusca.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout panelBuscaLayout = new javax.swing.GroupLayout(panelBusca);
        panelBusca.setLayout(panelBuscaLayout);
        panelBuscaLayout.setHorizontalGroup(
            panelBuscaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBuscaLayout.createSequentialGroup()
                .addGap(147, 147, 147)
                .addComponent(boxBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBuscaLayout.setVerticalGroup(
            panelBuscaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBuscaLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(boxBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelGeralLayout = new javax.swing.GroupLayout(panelGeral);
        panelGeral.setLayout(panelGeralLayout);
        panelGeralLayout.setHorizontalGroup(
            panelGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGeralLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelBusca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelGeralLayout.createSequentialGroup()
                        .addComponent(panelCtrl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(panelImg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(panelNotes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelGeralLayout.setVerticalGroup(
            panelGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGeralLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(panelGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelCtrl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelImg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelNotes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        seg = null;
        path = null;
        tagsModel = new DefaultListModel();
        listaTags.setModel(tagsModel);
        tags = new ListAnotacoes<>();
        CtrlBotoes(1, false);

        
        setVectorPesawat();
        boxModel = new DefaultComboBoxModel(vectorPesawat);
        boxBusca.setModel(boxModel);
        AutoCompleteDecorator.decorate(boxBusca);

        boxBusca.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        //System.out.println("Selected '"+boxBusca.getSelectedItem()+"'");
                    }
                });
            }
        });
    }

    /**
     * Este método é chamado após o botão "Segmentar" ser acionado. A função
     * dele é pegar uma imagem escolhida pelo usuário e fazer a segmentação dela.
     */
    private void buttonSegmentarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSegmentarActionPerformed
        //Pega os valores da interface
        double blur = (double) valBlur.getValue();
        int radius = (int) valRadius.getValue();
        int size = (int) valSize.getValue();

        seg = SegmentacaoDeImagem.segmentar(path, blur, radius, size);

        labelRegioes.setText("Total de regiões: " + seg.getTotalRegions());
        addImg(new ImageIcon(seg.getRegionMarkedImage()));
        CtrlBotoes(3, true);
    }//GEN-LAST:event_buttonSegmentarActionPerformed

    /**
     * Este método é chamado após o botão "Mostrar mapa de rótulos" ser
     * acionado. Chama o metodo estatico 'GerarMapaRotulos' da classe
     * SegmentacaoDeImagem.
     */
    private void buttonRotulosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRotulosActionPerformed
        SegmentacaoDeImagem.GerarMapaRotulos(seg);
        addImg(new ImageIcon(seg.getRegionMarkedImage()));
    }//GEN-LAST:event_buttonRotulosActionPerformed

    /**
     * Este método é chamado após o botão 'Selecionar Imagem' ser acionado Abre
     * um dialog para o usuário escolher uma imagem e mostra a imagem na tela.
     * Só aceita imagens jpg.
     */
    private void buttonImgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonImgActionPerformed
        //int returnVal = fileChooser.showOpenDialog(fileDialog);
        //if (returnVal == JFileChooser.APPROVE_OPTION) {
            //File file = fileChooser.getSelectedFile();
            File file = new File("imgs/model.jpg");  
            if (file.getName().contains("jpg")) {
                labelImg.setText("NomeImg: " + file.getName());
                path = file.getAbsolutePath();
                ConvertImage.scaleImage(DEFAULT_WIDTH, DEFAULT_HEIGHT, path);
                path = ConvertImage.getCaminhoDaImagem();
                ImageIcon image = new ImageIcon(path);
                addImg(image);
                CtrlBotoes(2, true);
            } else {
                CtrlBotoes(1, false);
                labelImg.setText("Utilize somente imagens jpg");
            }
        //}
    }//GEN-LAST:event_buttonImgActionPerformed

    /**
     * Atrela uma tag a uma região da imagem e adiciona a tag na lista de tags
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
            SegmentacaoDeImagem.AssocTagRegiao(tag, tags);
            tagsModel.addElement(tag);
            listaTags.setModel(tagsModel);
            CtrlBotoes(5, true);
        } else{
            JOptionPane.showMessageDialog(null, "Você não pode repetir a mesma anotação na imagem", "Anotações", ERROR_MESSAGE);
        }
        
        //Reset the text field.
        campoTag.requestFocusInWindow();
        campoTag.setText("");
        
        SegmentacaoDeImagem.RestaurarImg(1);
        addImg(new ImageIcon(seg.getRegionMarkedImage()));
        
    }//GEN-LAST:event_buttonAddActionPerformed
    
    /**
     * Destaca as regiões que foram salvas com o nome da tag selecionada. Chamado 
     * quando o usuário clica na lista de tags
     * @param evt 
     */
    private void listaTagsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaTagsMouseClicked
        if(tagsModel.size() > 0 || seg == null){
            String tag = (String)listaTags.getSelectedValue();
            SegmentacaoDeImagem.Selecionar(seg, tag, tags);
            addImg(new ImageIcon(seg.getRegionMarkedImage()));  
        }
    }//GEN-LAST:event_listaTagsMouseClicked
  
    /**
     * Limpa todas as seleções.
     * @param evt 
     */
    private void buttonClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonClearActionPerformed
        SegmentacaoDeImagem.RestaurarImg(1);
        addImg(new ImageIcon(seg.getRegionMarkedImage()));
        CtrlBotoes(4, false);
    }//GEN-LAST:event_buttonClearActionPerformed

    /**
     * Salva as anotações de uma imagem. Chamado após clicar no botão 
     * 'Salvar Anotações'.
     * @param evt 
     */
    private void buttonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSaveActionPerformed
        if(tags.size() > 0){
            tags.Salvar();
            JOptionPane.showMessageDialog(null, "Anotações salvas", "Salvo", INFORMATION_MESSAGE);
            CtrlBotoes(6, false);
        }
    }//GEN-LAST:event_buttonSaveActionPerformed

    /**
     * Deleta uma anotação da lista. Mas ainda não persiste no banco.
     * @param evt 
     */
    private void buttonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDeleteActionPerformed
        if(tagsModel.size() > 0 || seg == null){
            String tag = (String)listaTags.getSelectedValue();
            tagsModel.removeElement(tag);
            tags.Remover(tag);
            buttonClearActionPerformed(evt);
        }
    }//GEN-LAST:event_buttonDeleteActionPerformed

    /**
     * Adiciona uma imagem na interface e remove outras que estejam adicionadas.
     * @param img - Imagem a ser adicionada
     */
    private void addImg(ImageIcon image) {
        if (imagem != null) {
            panelImg.remove(imagem);
        }

        Image img = image.getImage();
        Image newimg = img.getScaledInstance(DEFAULT_WIDTH, DEFAULT_HEIGHT, java.awt.Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(newimg);
        imagem = new JLabel(newIcon);
        Dimension d = new Dimension(newimg.getWidth(rootPane), newimg.getHeight(rootPane));
        imagem.setMaximumSize(d);
        imagem.setSize(d);
        panelImg.add(imagem);
        revalidate();
        repaint();

        if(seg == null){
            return ;
        }
        
        imagem.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
               SegmentacaoDeImagem.setCoordenadas(e.getX(),e.getY(), imagem.getWidth());
               SegmentacaoDeImagem.destacarRegiao(seg);
               addImg(new ImageIcon(seg.getRegionMarkedImage()));
               CtrlBotoes(4, true);
            }
        });
    }
    
    /**
     * método feito só para teste, logo será apagado.
     * Ele preenche o vetor que vai ter os campos do jComboBox
     */
    public void setVectorPesawat(){
        int a;
        for(a=0;a<pesawat.length;a++){
            vectorPesawat.add(pesawat[a]);
        }
    }

    /**
     * 1 - todos
     * 2 - segmentar
     * 3 - rotulos
     * 4 - clear e add
     * 5 - save e delete
     * @param id
     * @param bool 
     */
    public void CtrlBotoes(int id, Boolean bool){
        switch (id){
            case 1:
                buttonSegmentar.setEnabled(bool);
                buttonRotulos.setEnabled(bool);
                buttonAdd.setEnabled(bool);
                buttonClear.setEnabled(bool);
                buttonDelete.setEnabled(bool);
                buttonSave.setEnabled(bool);
                break;
            case 2:
                buttonSegmentar.setEnabled(bool);
                break;
            case 3:
                buttonRotulos.setEnabled(bool);
                break;
            case 4:
                buttonClear.setEnabled(bool);
                buttonAdd.setEnabled(bool);
                break;
            case 5:
                buttonSave.setEnabled(bool);
                buttonDelete.setEnabled(bool);
                break;
        }
    }

    /**
     * Método que inicia o sistema.
     * @param args 
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
    private javax.swing.JLabel labelBlur;
    private javax.swing.JLabel labelImg;
    private javax.swing.JLabel labelRadius;
    private javax.swing.JLabel labelRegioes;
    private javax.swing.JLabel labelSize;
    private javax.swing.JList listaTags;
    private javax.swing.JPanel panelBusca;
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
