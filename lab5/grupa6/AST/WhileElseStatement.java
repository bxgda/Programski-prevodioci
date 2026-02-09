package AST;

import java.io.*;

public class WhileElseStatement extends Statement {
    private Expression condition;
    private Statement loopStatement;
    private Statement elseStatement;

    public WhileElseStatement(Expression cond, Statement loopS, Statement elseS) {
        this.condition = cond;
        this.loopStatement = loopS;
        this.elseStatement = elseS;
    }

    @Override
    public void translate(BufferedWriter out) throws IOException {
        String startLabel = ASTNode.genLab();
        String elseLabel = ASTNode.genLab();

        out.write(startLabel + ":");
        out.newLine();

        // Izračunaj uslov
        condition.translate(out);
        condition.genLoad("R1", out);

        // Ako je 0 (false), idi na else deo
        out.write("\tJumpIfZero\tR1, " + elseLabel);
        out.newLine();

        // Telo petlje
        loopStatement.translate(out);
        
        // Vrati se na početak provere
        out.write("\tJump\t" + startLabel);
        out.newLine();

        // Else deo (izvršava se jednom kad uslov padne)
        out.write(elseLabel + ":");
        out.newLine();
        elseStatement.translate(out);
    }
}