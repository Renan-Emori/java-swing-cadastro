import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.text.ParseException;

import javax.swing.JOptionPane;

import Pacientes.Paciente;

public class Pacientes extends PacienteForm {
	
	
	//selecionar da table
	
	public Pacientes() throws ParseException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void tblSelecionarClick(MouseEvent ev) {
		// TODO Auto-generated method stub
		tableMouseClicked(ev);
	}

	@Override
	protected void btnCadastrarClick(ActionEvent ev) {
		// TODO Auto-generated method stub
		
		String codigo = Integer.toString(table.getRowCount()+1);
		String nome = txtPaciente.getText();
		String nascimento = txtNascimento.getText();
		String endereco = txtEndereco.getText();
		String observacao = txtObservacoes.getText();
		
		Paciente p = new Paciente(codigo, nome, nascimento, endereco, observacao);
		
		if(validarCampos(nome, nascimento ,endereco) && codigoUnico(codigo)) {
			int resposta = JOptionPane.showConfirmDialog(null, "Criar registro?", "Deletar", JOptionPane.OK_CANCEL_OPTION);
			if(resposta == 0) {
				this.model.cadastrarPaciente(p);
				JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso!");
				limparCampos();
				CodigoNovo();
			}
		}
	}
	
	@Override
	protected void btnEditarClick(ActionEvent ev) {
		// TODO Auto-generated method stub
		
		int index = table.getRowCount();
		if(index == 0) {
			JOptionPane.showMessageDialog(this, "Não há usuários para editar");
		}else {
			lblEditar.setVisible(true);
			txtPesquisar.setVisible(true);
			btnAlterar.setVisible(true);
			pnlPe.setVisible(true);
			btnEditar.setVisible(false);
			btnListar.setVisible(false);	
		}
	}

	@Override
	protected void btnListarClick(ActionEvent ev) {
		// TODO Auto-generated method stub
		if(table.getRowCount()==0){
			JOptionPane.showMessageDialog(this, "Ainda não há pacientes cadastrados");
		}else {
			pnlPe.setVisible(true);
		}
	}
	
	@Override
	protected void btnAlterarClick(ActionEvent ev) {
		// TODO Auto-generated method stub
		
		int index = table.getSelectedRow();
		if(index == -1) {
			JOptionPane.showMessageDialog(this, "Selecione um usuário para editar!");
		}else {
			Paciente p = this.model.returnPaciente(index);
			int resposta = JOptionPane.showConfirmDialog(null, "Alterar registro?", "Deletar", JOptionPane.OK_CANCEL_OPTION);
			
			if(resposta == 0) {
				this.model.alterarPaciente(index, p);
				JOptionPane.showMessageDialog(this, "Usuário Editado com sucesso!");
				limparCampos();
				CodigoNovo();
			}
			
		}
		
	}


	@Override
	protected void btnExcluirClick(ActionEvent ev) {
		// TODO Auto-generated method stub
		int index = table.getSelectedRow();
		if(index == -1) {
			JOptionPane.showMessageDialog(this, "Escolha um usuário para remover primeiro");
		}else {
			int resposta = JOptionPane.showConfirmDialog(null, "Remover registro?", "Deletar", JOptionPane.OK_CANCEL_OPTION);
			
			if(resposta == 0) {
				this.model.removerPaciente(index);
				JOptionPane.showMessageDialog(this, "Usuário removido com sucesso!");
				limparCampos();
				CodigoNovo();			
			}			
		}
	}
	
	//métodos
	public void CodigoNovo() {
		String codigoString = Integer.toString(table.getRowCount()+1);
		txtCodigo.setText(codigoString);
	}
	
	public void limparCampos() {
		txtPaciente.setText("");
		txtNascimento.setText("");;
		txtEndereco.setText("");
		txtObservacoes.setText("");
	}
	
	public boolean validarCampos(String nome, String nascimento, String endereco) {
		//int codigoInt = Integer.getInteger(codigo);
		if(nome.trim().isEmpty() || nascimento.trim().isEmpty() || endereco.trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Campos não preenchidos");
			return false;
		}
		return true;
	}
	
	public boolean codigoUnico(String codigo) {
		if(this.model.pacienteExiste(codigo)) {
			JOptionPane.showMessageDialog(this, "Codigo não é único");
			return false;
		}
		return true;
	}
	
	public Paciente inserirDados() {
		String codigo = txtCodigo.getText();
		String nome = txtPaciente.getText();
		String nascimento = txtNascimento.getText();
		String endereco = txtEndereco.getText();
		String observacao = txtObservacoes.getText();
		
		Paciente p = new Paciente(codigo, nome, nascimento, endereco, observacao);
		return p;
	}

	public void tableMouseClicked(MouseEvent evt) {
		int index = table.getSelectedRow();
		Paciente p = this.model.returnPaciente(index);
		
		txtCodigo.setText(p.getCodigo());
		txtPaciente.setText(p.getNome());
		txtNascimento.setText(p.getNascimento());
		txtEndereco.setText(p.getEndereco());
		txtObservacoes.setText(p.getObservacoes());
	}
}
