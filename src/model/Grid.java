package model;

public final class Grid {
	private int[][] gridRule;
	private String[] gridLanguage;
	private String[] gridHeader;
	private int LanguagePosition = 1;
	private int HeaderPosition = 1;
	private String sequence = "";

	public Grid(int p, int l) {
		p++;
		l++;
		setGridRule(p, l);
		setGridLanguage(l);
		setGridHeader(p);
	}

	public Grid() {
	};

	public int[][] getGridRule() {
		return this.gridRule;
	}

	public int getGridValue(int q, int l) {
		return this.gridRule[q][l];
	}

	public void setGridRule(int p, int l) {
		this.gridRule = new int[p][l];
	}

	public void setGridRuleByPosition(String h, String l, String v) {
		if (!headerContainsNodo(h))
			setGridHeaderByPosition(h);

		if (!languageContainsLetter(l))
			setGridLanguageByPosition(l);

		if (!headerContainsNodo(v))
			setGridHeaderByPosition(v);

		this.gridRule[getHeaderPosition(h)][getLetterPosition(l)] = getHeaderPosition(v);
	}

	private void setGridLanguage(int l) {
		this.gridLanguage = new String[l];
	}

	private void setGridLanguageByPosition(String value) {
		this.gridLanguage[LanguagePosition] = value;
		LanguagePosition++;
	}

	private boolean languageContainsLetter(String letter) {
		for (String l : gridLanguage) {
			if (l != null && l.equals(letter))
				return true;
		}
		return false;
	}

	private int getLetterPosition(String letter) {
		for (int i = 1; i < gridLanguage.length; i++) {
			if (gridLanguage[i].equals(letter))
				return i;
		}
		return -1;
	}

	private void setGridHeader(int p) {
		this.gridHeader = new String[p];
	}

	private String getGridHeaderValue(int p) {
		return gridHeader[p];
	}

	private void setGridHeaderByPosition(String value) {
		this.gridHeader[HeaderPosition] = value;
		HeaderPosition++;
	}

	private int getHeaderPosition(String header) {
		for (int i = 1; i < gridHeader.length; i++) {
			if (gridHeader[i].equals(header))
				return i;
		}
		return -1;
	}

	private boolean headerContainsNodo(String nodo) {
		for (String n : gridHeader) {
			if (n != null && n.equals(nodo))
				return true;
		}
		return false;
	}

	public String getSequence() {
		return this.sequence == "" ? "" : this.sequence.substring(0, this.sequence.length() - 2);
	}

	private void setSequence(String sequence) {
		this.sequence += sequence + ", ";
	}

	private void loadDefaultSequence() {
		this.sequence = "";
	}

	public String validateWord(String word) {
		String[] splitWord = word.toUpperCase().split("");

		int qPosition = 1;
		int lPosition;
		loadDefaultSequence();
		for (String string : splitWord) {
			if (languageContainsLetter(string)) {
				lPosition = getLetterPosition(string);
				qPosition = getGridValue(qPosition, lPosition);
				if(getGridHeaderValue(qPosition) != null)
					setSequence(getGridHeaderValue(qPosition));
			} else {
				setSequence("qErro");
				return "Erro: Simbolo(s) Invalido(s)";
			}
		}
		return getGridHeaderValue(qPosition) == null ? "Palavra Invalida" : getGridHeaderValue(qPosition).contains("*") == true ? "Palavra Valida" : "Palavra Invalida";
	}

	public Grid loadDefaultGrid() {

		setGridRule(21, 5);
		setGridLanguage(5);
		setGridHeader(21);

		setGridRuleByPosition("q0", "A", "*q1q3q8");
		setGridRuleByPosition("q0", "B", "*q5q8");
		setGridRuleByPosition("q0", "C", "*q7");
		setGridRuleByPosition("*q1q3q8", "A", "q2");
		setGridRuleByPosition("*q1q3q8", "B", "q4q10");
		setGridRuleByPosition("*q1q3q8", "C", "q9");
		setGridRuleByPosition("*q5q8", "B", "q6q10");
		setGridRuleByPosition("*q5q8", "C", "q9");
		setGridRuleByPosition("*q7", "C", "q9");
		setGridRuleByPosition("q2", "A", "*q1q3q8");
		setGridRuleByPosition("q2", "B", "*q5q8");
		setGridRuleByPosition("q2", "B", "*q7");
		setGridRuleByPosition("q4q10", "B", "*q5q8");
		setGridRuleByPosition("q4q10", "C", "*q7");
		setGridRuleByPosition("q9", "C", "*q7");
		setGridRuleByPosition("q6q10", "B", "*q5q11");
		setGridRuleByPosition("q6q10", "C", "*q7");
		setGridRuleByPosition("*q5q11", "B", "q6q10");
		setGridRuleByPosition("*q5q11", "C", "q9");

		return this;
	}
}
