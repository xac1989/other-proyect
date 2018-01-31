package com.cencosud.mobile.list.util;

import java.util.ArrayList;
import java.util.List;

public final class ListUtil {

	private ListUtil() { }

	public static List<String> copy(List<String> source) {
		return source != null ? new ArrayList<>(source) : null;
	}
}
