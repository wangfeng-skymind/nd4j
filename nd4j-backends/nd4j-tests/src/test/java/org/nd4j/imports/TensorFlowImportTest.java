package org.nd4j.imports;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.Before;
import org.junit.Test;
import org.nd4j.autodiff.opstate.OpExecAction;
import org.nd4j.autodiff.opstate.OpState;
import org.nd4j.autodiff.samediff.SameDiff;
import org.nd4j.autodiff.samediff.impl.SDVariable;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.api.ops.Op;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.indexing.NDArrayIndex;
import org.nd4j.linalg.io.ClassPathResource;
import org.nd4j.linalg.util.HashUtil;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;


@Slf4j
public class TensorFlowImportTest {
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testHashEquality1() {
        long hash = HashUtil.getLongHash("Conv2DDerivative");
        assertEquals(-1637140380760460323L, hash);
    }


    @Test
    public void testHashEquality2() {
        long hash = HashUtil.getLongHash("switch");
        assertEquals(-1988317239813741487L, hash);
    }

    @Test
    public void testCustomOps1() {
        val map = Nd4j.getExecutioner().getCustomOperations();

        assertTrue(map.size() > 0);
    }

    @Test
    public void importGraph1() throws Exception {
        SameDiff graph = TensorFlowImport.importGraph(new ClassPathResource("tf_graphs/max_add_2.pb.txt").getFile());

        assertNotNull(graph);

        assertEquals(2, graph.getVariableMap().size());
        assertEquals(2, graph.getGraph().getInputs().size());
        assertEquals(1, graph.getGraph().getOpOrder().getActions().size());

        List<OpExecAction> actions = graph.getGraph().getOpOrder().getActions();
        assertEquals(1, actions.size());

        OpState state = actions.get(0).getOpState();

        assertEquals(Op.Type.TRANSFORM, state.getOpType());
        assertEquals(0, state.getOpNum());

        SDVariable var0 = graph.getVariableMap().get("zeros");
        SDVariable var1 = graph.getVariableMap().get("ones");

        assertNotNull(var0);
        assertNotNull(var1);

        assertNotNull(var0.getArr());
        assertNotNull(var1.getArr());

        assertEquals(0.0, var0.getArr().sumNumber().doubleValue(), 1e-5);
        assertEquals(12.0, var1.getArr().sumNumber().doubleValue(), 1e-5);
    }


    @Test
    public void importGraph2() throws Exception {
        SameDiff graph = TensorFlowImport.importGraph(new ClassPathResource("tf_graphs/tensorflow_inception_graph.pb").getFile());

        assertNotNull(graph);
    }


    @Test
    public void importGraph3() throws Exception {
        SameDiff graph = TensorFlowImport.importGraph(new ClassPathResource("tf_graphs/max_log_reg.pb.txt").getFile());

        assertNotNull(graph);
    }


    @Test
    public void importGraph4() throws Exception {
        SameDiff graph = TensorFlowImport.importGraph(new ClassPathResource("tf_graphs/max_multiply.pb.txt").getFile());

        assertNotNull(graph);

        val p0 = Nd4j.create(10, 10).assign(2.0);
        val p1 = Nd4j.create(10, 10).assign(3.0);


        graph.getVariableMap().get("Placeholder").setArr(p0);
        graph.getVariableMap().get("Placeholder_1").setArr(p1);

        graph.getVertexToArray().put("Placeholder", p0);
        graph.getVertexToArray().put("Placeholder_1", p1);


//        graph.var("Placeholder", p0);
//        graph.var("Placeholder_1", p1);

        val res = graph.execAndEndResult();



        assertEquals(6.0, res.meanNumber().doubleValue(), 1e-5);
    }


    @Test
    public void testIntermediate1() throws Exception {
        Nd4j.create(1);
        val tg = TensorFlowImport.importIntermediate(new ClassPathResource("tf_graphs/tensorflow_inception_graph.pb").getFile());

        assertTrue(tg.getVariableSpace().hasVariable("input"));
        assertTrue(tg.getVariableSpace().getVariable("input").isPlaceholder());

        val ipod = Nd4j.read(new DataInputStream(new FileInputStream(new ClassPathResource("tf_graphs/ipod.nd4").getFile())));

        tg.provideArrayForVariable("input", ipod);

        val buffer = tg.asFlatBuffers();
        assertNotNull(buffer);
/*
        val offset = buffer.position();

        log.info("Length: {}; Offset: {};", buffer.capacity(), offset);
        val array = buffer.array();

        try (val fos = new FileOutputStream("../../libnd4j/tests/resources/inception.fb"); val dos = new DataOutputStream(fos)) {
            dos.write(array, offset, array.length - offset);
        }
        */
    }

    @Test
    public void testDefaultArgs() {
        val op = Nd4j.getOpFactory().getOpByName("relu");

        val extras = op.extraArgs();
        assertTrue(extras.length == 1);
        val value = (Double) extras[0];

        assertEquals(0.0f, value.floatValue(), 1e-5f);
    }
}