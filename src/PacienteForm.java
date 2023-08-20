import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;

import Pacientes.PacienteModel;

public abstract class PacienteForm extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	PacienteModel model = new PacienteModel();
	TableRowSorter<PacienteModel> sorter = new TableRowSorter<PacienteModel>(model);

	//panels
	protected JPanel pnlBtn;
	protected JPanel pnlForm;
	protected JPanel pnlPe;
	
	//buttons
	protected JButton btnCadastrar;
	protected JButton btnEditar;
	protected JButton btnListar;
	protected JButton btnExclusao;
	
	//table
	protected JTable table;
	protected JTextField txtPesquisar;
	protected JButton btnAlterar;
	protected JLabel lblEditar;

	//código
	protected JLabel lblCodigo;
	protected JTextField txtCodigo;
	//Nome do paciente
	protected JLabel lblPaciente;
	protected JTextField txtPaciente;
	//Data de nascimento
	protected JLabel lblNascimento;
	protected JTextField txtNascimento;
	//Endereço
	protected JLabel lblEndereco;
	protected JTextField txtEndereco;
	//Observações
	protected JLabel lblObservacoes;
	protected JTextField txtObservacoes;
	
	public PacienteForm() {
		this.inicializar();
		this.eventos();
	}
	
	private void inicializar() {	
		this.setTitle("Pacientes");		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(getPnlForm(), BorderLayout.NORTH);
		this.getContentPane().add(getPnlBtn(), BorderLayout.CENTER);
		this.getContentPane().add(getPnlPe(), BorderLayout.SOUTH);
		this.pack();
		
		txtPesquisar.setVisible(false);
		btnAlterar.setVisible(false);
		lblEditar.setVisible(false);
		pnlPe.setVisible(false);
		}
	
	//eventos
	protected abstract void btnCadastrarClick(ActionEvent ev);
	protected abstract void btnEditarClick(ActionEvent ev);
	protected abstract void btnListarClick(ActionEvent ev);
	protected abstract void btnExcluirClick(ActionEvent ev);
	protected abstract void btnAlterarClick(ActionEvent ev);
	
	private void eventos() {
		//cadastrar
		btnCadastrar.addActionListener(this::btnCadastrarClick);
		
		//editar
		btnEditar.addActionListener(this::btnEditarClick);
		
		//listar
		btnListar.addActionListener(this::btnListarClick);
		
		//exclusao
		btnExclusao.addActionListener(this::btnExcluirClick);
		
		//alterar
		btnAlterar.addActionListener(this::btnAlterarClick);
				}
	
	public JPanel getPnlForm() {
		if(pnlForm == null) {
			pnlForm = new JPanel(new GridLayout(5, 2));
			
			//Codigo
			lblCodigo = new JLabel("Código");
			txtCodigo = new JTextField(2);
			txtCodigo.setText("1");
			txtCodigo.setEditable(false);
			
			//Nome do paciente
			  lblPaciente = new JLabel("Nome");
			  txtPaciente = new JTextField(20);
			  
			//Data de nascimento
			lblNascimento = new JLabel("Nascimento (dd/mm/yyyy)");
			txtNascimento = new JTextField(10);
			txtNascimento.setToolTipText("Formato: dd/mm/yyyy");
			txtNascimento.setInputVerifier(new InputVerifier() {
				@Override
				public boolean verify(JComponent input) {
					String text = txtNascimento.getText();
					if (!text.matches("\\d{2}/\\d{2}/\\d{4}")) {
			            JOptionPane.showMessageDialog(null, "Formato de data inválida!");
			            return false;
			        }
					return true;
				}				
			});

			//Endereço
			lblEndereco = new JLabel("Endereço");
			txtEndereco = new JTextField(20);
			//Observações
			lblObservacoes = new JLabel("Observações");
			txtObservacoes = new JTextField(20);
			
			pnlForm.add(lblCodigo);
			pnlForm.add(txtCodigo);
			
			pnlForm.add(lblPaciente);
			pnlForm.add(txtPaciente);
			
			pnlForm.add(lblNascimento);
			pnlForm.add(txtNascimento);
			
			pnlForm.add(lblEndereco);
			pnlForm.add(txtEndereco);
			
			pnlForm.add(lblObservacoes);
			pnlForm.add(txtObservacoes);
		}
		return pnlForm;
	}
	
	public JPanel getPnlBtn() {
		if(pnlBtn == null) {
			pnlBtn = new JPanel(new FlowLayout(FlowLayout.LEFT));
			
			//cadastrar
			btnCadastrar = new JButton("Cadastrar novo");
			btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btnCadastrar.setBounds(10, 39, 100, 36);
			pnlBtn.add(btnCadastrar);	
			
			//editar
			btnEditar = new JButton("Edição");
			btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btnCadastrar.setBounds(10, 39, 100, 36);
			pnlBtn.add(btnEditar);
			
			//listar
			btnListar = new JButton("Listar Todos");
			btnListar.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btnListar.setBounds(224, 39, 120, 36);
			pnlBtn.add(btnListar);
			
			//excluir
			btnExclusao = new JButton("Excluir");
			btnExclusao.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btnExclusao.setBounds(354, 39, 100, 36);
			pnlBtn.add(btnExclusao);
		}
		return pnlBtn;
	}

	public JPanel getPnlPe() {
		if(pnlPe == null) {
			pnlPe = new JPanel(new BorderLayout());
			txtPesquisar = new JTextField(20);
			btnAlterar = new JButton("Editar");
			lblEditar = new JLabel("Pesquise pelo nome:");
			
			//TableRowSorter<PacienteModel> myTableRowSorter = new TableRowSorter<PacienteModel>(model);
			
			table = new JTable(model);
			table.setRowSorter(sorter);
			txtPesquisar.getDocument().addDocumentListener(new DocumentListener(){

				@Override
				public void insertUpdate(DocumentEvent e) {
					model.fireTableDataChanged();
				}

				@Override
				public void removeUpdate(DocumentEvent e) {
					model.fireTableDataChanged();					
				}

				@Override
				public void changedUpdate(DocumentEvent e) {
					model.fireTableDataChanged();					
				}
				
			});
			
			sorter.setRowFilter(new RowFilter() {
				@Override
				public boolean include(Entry entry) {
					String name = entry.getValue(1).toString().toLowerCase();
					String searchText = txtPesquisar.getText().toLowerCase();
					return name.startsWith(searchText);
				}
			});

			pnlBtn.add(lblEditar);
			pnlBtn.add(txtPesquisar);
			pnlBtn.add(btnAlterar);	
			pnlPe.add(new JScrollPane(table));	

		}
		return pnlPe;
	}
}
