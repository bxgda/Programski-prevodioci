package AST;

import java.io.*;
import java.util.ArrayList;

import SymbolTable.Variable;

public class ApplyStatement extends Statement {
	private String tempName;
	private ArrayList nameList;
	private Statement body;
	
	public ApplyStatement( String tempName, ArrayList nameList, Statement body )
	{
		this.tempName = tempName;
		this.nameList = nameList;
		this.body = body;
	}
	
	public void translate( BufferedWriter out )
	throws IOException
	{
		for ( int i = 0; i < nameList.size(); i++ )
		{
			Variable current = (Variable) nameList.get( i );
			out.write( "\tLoad_Mem\t" + "R1, " + current.name );
			out.newLine();
			out.write( "\tStore\t\t" + "R1, " + tempName );
			out.newLine();
			body.translate( out );
		}
	}
}
