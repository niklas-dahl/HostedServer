package com.hosted.base.managing;

import java.util.Iterator;
import java.util.LinkedList;

import com.hosted.tables.utils.Table;

public class TableManager implements Iterable<Table> {

	private static TableManager tableManager;

	private LinkedList<Table> tables;

	private TableManager() {
		tables = new LinkedList<>();
	}

	public Table get(int i) {
		return tables.get(i);
	}

	public void put(Table table) {
		tables.add(table);
	}

	public void remove(Table table) {
		tables.remove(table);
	}

	public void remove(int i) {
		tables.remove(i);
	}

	protected static TableManager getTableManager() {
		if (tableManager == null) {
			tableManager = new TableManager();
		}
		return tableManager;
	}

	@Override
	public Iterator<Table> iterator() {
		return tables.iterator();
	}

}
