package log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

//This custom formatter formats parts of a log record to a single line
class HtmlFormatter extends Formatter {
	// This method is called for every log records
	public String format(LogRecord rec) {
		StringBuffer buf = new StringBuffer(1000);
		// Bold any levels >= WARNING
		buf.append("<tr><td>");
		if (rec.getLevel().intValue() >= Level.WARNING.intValue()) {
			buf.append("<b>");
			buf.append(rec.getLevel());
			buf.append("</b>");
		} else {
			buf.append(rec.getLevel());
		}
		buf.append("</td><td>");
		buf.append(calcDate(rec.getMillis()));
		buf.append("</td><td>");
		buf.append(rec.getLoggerName());
		buf.append("</td><td>");
		if (rec.getSourceMethodName().equals("<init>")) {
			buf.append("&lsaquo;init&rsaquo;");
		} else {
			buf.append(rec.getSourceMethodName());
		}
		buf.append("</td><td>");
		buf.append(rec.getThreadID());
		buf.append("</td><td>");
		buf.append(formatMessage(rec));
		buf.append("</td></tr>\n");
		return buf.toString();
	}

	private String calcDate(long millisecs) {
		SimpleDateFormat date_format = new SimpleDateFormat("MMM dd,yyyy HH:mm");
		Date resultdate = new Date(millisecs);
		return date_format.format(resultdate);
	}

	// This method is called just after the handler using this
	// formatter is created
	public String getHead(Handler h) {
		return "<table width=\"100%\" border>\n" + "<tr><th>Level</th>"
				+ "<th>Time</th>" + "<th>Logger Name</th>" + "<th>Method</th>"
				+ "<th>Thread Id</th>" + "<th>Log Message</th>" + "</tr>";
	}

	// This method is called just after the handler using this
	// formatter is closed
	public String getTail(Handler h) {
		return "</table>\n";
	}
}