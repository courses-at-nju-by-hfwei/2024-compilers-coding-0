package expr;

import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import expr.precedence.ExprArrayPrecLexer;
import expr.precedence.ExprArrayPrecParser;

public class ExprArrayPrecTest {
  public static void main(String[] args) throws IOException {
    InputStream is = new FileInputStream(
        Path.of("src/test/antlr/expr/precedence/exprarray.txt").toFile());

    CharStream input = CharStreams.fromStream(is);
    ExprArrayPrecLexer lexer = new ExprArrayPrecLexer(input);
    CommonTokenStream tokens = new CommonTokenStream(lexer);

    ExprArrayPrecParser parser = new ExprArrayPrecParser(tokens);
    ParseTree tree = parser.stat();

    // WARNING: The remaining code is generated by ChatGPT!!!
    JFrame frame = new JFrame("Parse Tree");
    JPanel panel = new JPanel();

    TreeViewer viewer = new TreeViewer(Arrays.asList(
        parser.getRuleNames()), tree);
    viewer.setScale(3.0);

    panel.add(viewer);
    frame.add(panel);

    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setSize(800, 600);
    frame.setVisible(true);
  }
}