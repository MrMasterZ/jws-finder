package org.example;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@Entity                                                    // аннотация, регистрирующая класс как сущность БД
@Table(name = "TEST")                                      // связываем с конкретной таблицей по названию
@XmlRootElement(name = "Result")                           // определяем корневой элемент XML
@XmlType(propOrder = { "code", "fileNames", "error" })     // определяем последовательность тегов в XML
public class Result {
	@Id
	private int id = 1;
	@Column(name = "CODE")
	private String code = "01.Result.NotFound";
	@Column(name = "\"NUMBER\"")
	private int number = 0;
	@Column(name = "FILENAMES")
	private String fileNames = "";
	@Column(name = "ERROR")
	private String error = "";

	public Result() {

	}

	public Result(String code, String number, List<String> fileNames, String error) {
		this.code = code;
		this.number = Integer.parseInt(number);
		this.fileNames = fileNames.toString();
		this.error = error;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = Integer.parseInt(number);
	}

	public String getFileNames() {
		return fileNames;
	}

	public void setFileNames(List<String> fileNames) {
		this.fileNames = fileNames.toString();
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return "Result{" + "code=" + code + ", fileNames=" + fileNames + ", error=" + error + '}';
	}
}
