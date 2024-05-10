package codegen;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

public class CodeGenVisitorTest {
  private InputStream is = System.in;
  private final String PATH = "src/test/antlr/codegen/control-flow-I/";
  private String srcFile;
  private String codeFile;

  @BeforeMethod
  public void setUp() throws IOException {
    // bool.txt, if.txt, while.txt, break.txt, bool-short-circuit.txt
    // while-if-II.txt, bool-short-circuit-II.txt
    srcFile = "while-if-II";
    is = new FileInputStream(Path.of(PATH + srcFile + ".txt").toFile());
  }

  @Test
  public void testCodeGenVisitor() throws IOException {
    CharStream input = CharStreams.fromStream(is);
    ControlLexer lexer = new ControlLexer(input);
    CommonTokenStream tokens = new CommonTokenStream(lexer);

    ControlParser parser = new ControlParser(tokens);
    ParseTree tree = parser.prog();

    codeFile = srcFile + "-code";
    CodeGenVisitor cg = new CodeGenVisitor(PATH + codeFile + ".txt");
    cg.visit(tree);
  }
}