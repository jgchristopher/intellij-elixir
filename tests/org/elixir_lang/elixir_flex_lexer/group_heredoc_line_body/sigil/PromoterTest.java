package org.elixir_lang.elixir_flex_lexer.group_heredoc_line_body.sigil;

import com.intellij.psi.tree.IElementType;
import org.elixir_lang.ElixirFlexLexer;
import org.elixir_lang.elixir_flex_lexer.TokenTest;
import org.elixir_lang.psi.ElixirTypes;
import org.junit.Ignore;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by luke.imhoff on 9/6/14.
 */
@Ignore("abstract")
public abstract class PromoterTest extends TokenTest {
    /*
     * Constructors
     */

    public PromoterTest(CharSequence charSequence, IElementType tokenType, int lexicalState) {
        super(charSequence, tokenType, lexicalState);
    }

    /*
     * Methods
     */

    public static Collection<Object[]> generateData(IElementType fragmentType) {
        return Arrays.asList(
                new Object[][] {
                        { ";", fragmentType, ElixirFlexLexer.GROUP_HEREDOC_LINE_BODY },
                        { "\n", ElixirTypes.EOL, ElixirFlexLexer.GROUP_HEREDOC_LINE_START },
                        { "\r\n", ElixirTypes.EOL, ElixirFlexLexer.GROUP_HEREDOC_LINE_START },
                        { "a", fragmentType, ElixirFlexLexer.GROUP_HEREDOC_LINE_BODY }
                }
        );
    }

    protected void reset(CharSequence charSequence) throws IOException {
        // start to trigger GROUP state
        CharSequence fullCharSequence = "~" + sigilName() + "'''\n" + charSequence;
        super.reset(fullCharSequence);
        // consume '~'
        flexLexer.advance();
        // consume sigilName
        flexLexer.advance();
        // consume "'''"
        flexLexer.advance();
        // consume '\n'
        flexLexer.advance();
    }

    protected abstract char sigilName();
}
