import java.awt.Event;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import org.eclipse.swt.widgets.Display; 
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class todo {

	protected Shell shell;
	private Text input;
	private Text output;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			todo window = new todo();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("Task Manager");
		final TextBuddy tb = new TextBuddy("todo.txt");
		input = new Text(shell, SWT.BORDER);
		input.setBounds(47, 33, 292, 23);
		//This event triggered on enter key
		input.addListener(SWT.Traverse, new Listener()
	    {
			
			@Override
			public void handleEvent(org.eclipse.swt.widgets.Event event) {
				if(event.detail == SWT.TRAVERSE_RETURN)
					try {
						output.setText("");
						tb.readFromFile();
						String command = input.getText();
						output.setText(tb.executeCommand(command));
						input.setText("");
					} catch (Exception e1) {
						e1.printStackTrace();
					}
			}
	    });
		

		output = new Text(shell, SWT.BORDER|SWT.WRAP);
		output.setText("Welcome to TextBuddy.\n");
		output.setBounds(47, 91, 292, 132);

		/*Button btnEnter = new Button(shell, SWT.NONE);
		// This one triggered on btn pressed.
		btnEnter.addSelectionListener(new SelectionAdapter() {
			TextBuddy tb = new TextBuddy();
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					tb.content = tb.readFileToList();
					output.setText("Welcome to TextBuddy."+tb.fileName+" is ready for use. ");
					String command = input.getText();
					output.setText(tb.executeCommand(command));
					tb.writeIntoFile();
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});
		
		btnEnter.setBounds(272, 31, 65, 27);
		btnEnter.setText("enter");*/

	}
}
