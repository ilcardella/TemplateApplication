package it.polimi.template.model.editor;

import it.polimi.template.model.*;
import java.util.Date;

public class Clock implements Node {

	private Date date;

	@Override
	public Mission run(Mission m) {

		return m;
	}

}
