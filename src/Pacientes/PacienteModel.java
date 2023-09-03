package Pacientes;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

//Model
public class PacienteModel extends AbstractTableModel {
	
	static ArrayList<Paciente> pacientes = new ArrayList<>();
	private String[] columnNames = {"Código","Nome", "Nascimento", "Endereço", "Observações"};
	
	public void cadastrarPaciente(Paciente p) {
		pacientes.add(p);
		this.fireTableDataChanged();
	}
	
	public Paciente returnPaciente(int index) {
		return pacientes.get(index);
	}
	
	public void alterarPaciente(int index, Paciente p) {
		pacientes.set(index, p);
		this.fireTableDataChanged();
	}
	
	public void removerPaciente(int index) {
		pacientes.remove(index);
		this.fireTableDataChanged();
	}
	
	public boolean pacienteExiste(String codigo) {
		if(pacientes.contains(new Paciente(codigo, null, null, null, null)
				)) {
			return true;
		}
		return false;
	}
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return pacientes.size();
	}
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}
	
	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}
	

	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		if(columnIndex == 0) {
			return pacientes.get(rowIndex).getCodigo();
		}else if (columnIndex == 1) {
			return pacientes.get(rowIndex).getNome();
		}else if (columnIndex == 2) {
			return pacientes.get(rowIndex).getNascimento();
		}else if (columnIndex == 3) {
			return pacientes.get(rowIndex).getEndereco();
		}else {
			return pacientes.get(rowIndex).getObservacoes();
		}
	}
}
