/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package info.joaolopes.Prefixr;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JEditorPane;
import javax.swing.text.JTextComponent;
import org.openide.awt.ActionRegistration;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionID;
import org.openide.cookies.EditorCookie;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle.Messages;

@ActionID(category = "Edit",
id = "info.joaolopes.Prefixr.PrefixrAction")
@ActionRegistration(iconBase = "info/joaolopes/Prefixr/prefixr.PNG",
displayName = "#CTL_PrefixrAction")
@ActionReferences({
    @ActionReference(path = "Menu/Refactoring", position = 2125, separatorBefore = 2112),
    @ActionReference(path = "Toolbars/Build", position = 500),
    @ActionReference(path = "Shortcuts", name = "DO-P")
})
@Messages("CTL_PrefixrAction=Prefixr")
public final class PrefixrAction implements ActionListener {

    private final EditorCookie context;

    public PrefixrAction(EditorCookie context) {
           this.context = context;
    }
    private JTextComponent getit(EditorCookie context) throws Exception{
        for (JEditorPane pane : context.getOpenedPanes()) return pane;
        throw new Exception("Error getting the editor.");
    }
    
    public void actionPerformed(ActionEvent e) {
        try{
            JTextComponent textComp = getit(context);
            String text = textComp.getSelectedText();
            if (textComp.getSelectionStart() == textComp.getSelectionEnd()){
                textComp.selectAll();
                text = textComp.getSelectedText();
            }
            
            textComp.replaceSelection(AskPrefixr.now(text));
        } catch (Exception ex) {
                Exceptions.printStackTrace(ex);
        }
    }
}
