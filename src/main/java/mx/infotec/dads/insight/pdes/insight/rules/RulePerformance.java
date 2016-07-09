package mx.infotec.dads.insight.pdes.insight.rules;

import mx.infotec.dads.insight.pdes.insight.Message;
import mx.infotec.dads.insight.pdes.insight.MessageType;
import mx.infotec.dads.insight.pdes.model.PerformanceReportTable;

public class RulePerformance extends RuleBase {

    private PerformanceReportTable data;

    public RulePerformance(PerformanceReportTable data) {
	this.data = data;
    }

    @Override
    public boolean evaluate() {
	return "".equals(data.getVgDiff());
    }

    @Override
    public Message getMessage() {
	return Message.getBuilder().addMessageType(MessageType.ADVICE)
		.addText("Es posible que no se esté agregando el detalle suficiente en tus actividades").addnewLine()
		.addText("Trata de registrar más tiempo").build();
    }
}
