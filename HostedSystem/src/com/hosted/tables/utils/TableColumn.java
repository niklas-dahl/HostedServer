package com.hosted.tables.utils;

import com.hosted.base.Logger;

public class TableColumn {
	
	private static final Logger log = Logger.getLogger(TableColumn.class);

	private String name;
	private String foreignKey;

	private ColumnType type;

	private String notNull;
	private String unique;
	private String autoIncrement;
	private String primaryKey;
	private String def;
	private String maxLength;


	public TableColumn(String name, ColumnType type) {
		this(name, type, false, false);
	}

	public TableColumn(String name, ColumnType type, boolean notNull) {
		this(name, type, notNull, false);
	}

	public TableColumn(String name, ColumnType type, boolean notNull, boolean unique) {
		this.name = name;
		this.type = type;
		setNotNull(notNull);
		setUnique(unique);
	}

	public TableColumn setNotNull(boolean notNull) {
		this.notNull = notNull ? "not null" : null;
		return this;
	}

	public TableColumn setUnique(boolean unique) {
		this.unique = unique ? "unique" : null;
		return this;
	}

	public TableColumn setAutoIncrement(boolean autoIncrement) {
		this.autoIncrement = autoIncrement ? "auto_increment" : null;
		return this;
	}

	public TableColumn setPrimary(boolean primary) {
		this.primaryKey = primary ? "primary" : null;
		return this;
	}

	public TableColumn setDefault(String def) {
		this.def = def;
		return this;
	}

	public TableColumn setMaxLength(String maxLength) {
		this.maxLength = maxLength;
		return this;
	}

	public TableColumn setForeignKey(String tableName, String column) {
		this.foreignKey = tableName != null ? ", foreign key(" + name +") references " + tableName + "(" + column + ")" : null;
		return this;
	}

	@Override
	public String toString() {
		return buildString();
	}

	private String buildString() {
		StringBuilder sb = new StringBuilder();
		sb.append(name);
		sb.append(" " + type.toString());
		// max length
		if (maxLength != null) {
			sb.append("(" + maxLength + ")");
		}
		// notNull
		if (notNull != null) {
			sb.append(" " + notNull);
		}
		// autoIncrement
		if (autoIncrement != null) {
			sb.append(" " + autoIncrement);
		}
		// unique
		if (unique != null) {
			sb.append(" " + unique);
		}
		// primary
		if (primaryKey != null) {
			sb.append(" " + primaryKey);
		}
		// foreign
		if (foreignKey != null) {
			sb.append(" " + foreignKey);
		}
		// def
		if (def != null) {
			sb.append(" default " + def);
		}
		
		log.debug("created column: " + sb.toString());
		return sb.toString();

	}

}
