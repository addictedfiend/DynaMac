/******************************************************
* Created by Marneus901                                *
* � 2012 MarneusScripts.com                            *
* **************************************************** *
* Access to this source is unauthorized without prior  *
* authorization from its appropriate author(s).        *
* You are not permitted to release, nor distribute this* 
* work without appropriate author(s) authorization.    *
********************************************************/
package org.dynamac.bot.api.wrappers;

import org.dynamac.environment.Data;
import org.dynamac.reflection.ClassHook;
import org.dynamac.reflection.FieldHook;

public class InterfaceNode extends Node{
	public Object currentObject;
	public static ClassHook currentHook;
	private static FieldHook mainID;
	public InterfaceNode(Object o){
		super(o);
		currentObject = o;
		if(currentHook==null){
			currentHook = Data.runtimeClassHooks.get("InterfaceNode");
			mainID = currentHook.getFieldHook("getMainID");
		}
	}
	public static void resetHooks(){
		currentHook=null;
		mainID=null;
	}
	public int getMainID(){
		if(mainID==null)
			mainID = currentHook.getFieldHook("getMainID");
		if(mainID!=null){
			Object data = mainID.get(currentObject);
			if(data!=null)
				return (Integer)data * mainID.getIntMultiplier();
		}
		return -1;		
	}
}
