package br.com.trajy.consultacreditoapi.integration;

import static br.com.trajy.consultacreditoapi.util.BigDecimalUtil.formatBigDecimalToJsonPathResultMatchers;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.trajy.consultacreditoapi.model.entity.Credito;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@ComponentScan("br.com.trajy.consultacreditoapi.controller")
@AutoConfigureMockMvc
class CreditoIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    @DisplayName("Deve Retornar Dados de Credito Quando Informado o Numero de Credito")
    void deveRetornarDadosCreditoQuandoInformadoNumeroCredito() throws Exception {

        //Arrange
        String numeroCredito = "123456";
        String querySql = "SELECT * FROM credito WHERE numero_credito = ?";

        Credito credito = jdbcTemplate.queryForObject(querySql, (rs, rowNum) -> {
            Credito entity = new Credito();
            entity.setNumeroCredito(rs.getString("numero_credito"));
            entity.setNumeroNfse(rs.getString("numero_nfse"));
            entity.setDataConstituicao(rs.getDate("data_constituicao").toLocalDate());
            entity.setValorIssqn(formatBigDecimalToJsonPathResultMatchers(rs.getBigDecimal("valor_issqn")));
            entity.setTipoCredito(rs.getString("tipo_credito"));
            entity.setSimplesNacional(rs.getBoolean("simples_nacional"));
            entity.setAliquota(formatBigDecimalToJsonPathResultMatchers(rs.getBigDecimal("aliquota")));
            entity.setValorFaturado(formatBigDecimalToJsonPathResultMatchers(rs.getBigDecimal("valor_faturado")));
            entity.setValorDeducao(formatBigDecimalToJsonPathResultMatchers(rs.getBigDecimal("valor_deducao")));
            entity.setBaseCalculo(formatBigDecimalToJsonPathResultMatchers(rs.getBigDecimal("base_calculo")));
            return entity;
        }, numeroCredito);

        //Action
        mockMvc.perform(get("/creditos/credito/{numeroCredito}", numeroCredito))

        //Assert
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numeroCredito").value(credito.getNumeroCredito()))
                .andExpect(jsonPath("$.numeroNfse").value(credito.getNumeroNfse()))
                .andExpect(jsonPath("$.dataConstituicao").value(credito.getDataConstituicao().toString()))
                .andExpect(jsonPath("$.valorIssqn").value(credito.getValorIssqn()))
                .andExpect(jsonPath("$.tipoCredito").value(credito.getTipoCredito()))
                .andExpect(jsonPath("$.simplesNacional").value(credito.isSimplesNacional()))
                .andExpect(jsonPath("$.aliquota").value(credito.getAliquota()))
                .andExpect(jsonPath("$.valorFaturado").value(credito.getValorFaturado()))
                .andExpect(jsonPath("$.baseCalculo").value(credito.getBaseCalculo()))
                .andDo(print());
    }

    @Test
    @DisplayName("Deve Retornar Lista Dados de Credito Quando Informado o da NumeroNfse")
    void deveRetornarListaDadosCreditoQuandoInformadoNumeroNfse() throws Exception {

        //Arrange
        String numeroNfse = "7891011";
        String querySql = "SELECT * FROM credito WHERE numero_nfse = ?";

        List<Credito> creditos = jdbcTemplate.query(querySql, (rs, rowNum) -> {
            Credito entity = new Credito();
            entity.setNumeroCredito(rs.getString("numero_credito"));
            entity.setNumeroNfse(rs.getString("numero_nfse"));
            entity.setDataConstituicao(rs.getDate("data_constituicao").toLocalDate());
            entity.setValorIssqn(formatBigDecimalToJsonPathResultMatchers(rs.getBigDecimal("valor_issqn")));
            entity.setTipoCredito(rs.getString("tipo_credito"));
            entity.setSimplesNacional(rs.getBoolean("simples_nacional"));
            entity.setAliquota(formatBigDecimalToJsonPathResultMatchers(rs.getBigDecimal("aliquota")));
            entity.setValorFaturado(formatBigDecimalToJsonPathResultMatchers(rs.getBigDecimal("valor_faturado")));
            entity.setValorDeducao(formatBigDecimalToJsonPathResultMatchers(rs.getBigDecimal("valor_deducao")));
            entity.setBaseCalculo(formatBigDecimalToJsonPathResultMatchers(rs.getBigDecimal("base_calculo")));
            return entity;
        }, numeroNfse);

        //Action
        mockMvc.perform(get("/creditos/{numeroNfse}", numeroNfse))

        //Assert
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].numeroCredito").value(creditos.get(0).getNumeroCredito()))
                .andExpect(jsonPath("$[0].numeroNfse").value(creditos.get(0).getNumeroNfse()))
                .andExpect(jsonPath("$[0].dataConstituicao").value(creditos.get(0).getDataConstituicao().toString()))
                .andExpect(jsonPath("$[0].valorIssqn").value(creditos.get(0).getValorIssqn()))
                .andExpect(jsonPath("$[0].tipoCredito").value(creditos.get(0).getTipoCredito()))
                .andExpect(jsonPath("$[0].simplesNacional").value(creditos.get(0).isSimplesNacional()))
                .andExpect(jsonPath("$[0].aliquota").value(creditos.get(0).getAliquota()))
                .andExpect(jsonPath("$[0].valorFaturado").value(creditos.get(0).getValorFaturado()))
                .andExpect(jsonPath("$[0].baseCalculo").value(creditos.get(0).getBaseCalculo()))
                .andExpect(jsonPath("$[1].numeroCredito").value(creditos.get(1).getNumeroCredito()))
                .andExpect(jsonPath("$[1].numeroNfse").value(creditos.get(1).getNumeroNfse()))
                .andExpect(jsonPath("$[1].dataConstituicao").value(creditos.get(1).getDataConstituicao().toString()))
                .andExpect(jsonPath("$[1].valorIssqn").value(creditos.get(1).getValorIssqn()))
                .andExpect(jsonPath("$[1].tipoCredito").value(creditos.get(1).getTipoCredito()))
                .andExpect(jsonPath("$[1].simplesNacional").value(creditos.get(1).isSimplesNacional()))
                .andExpect(jsonPath("$[1].aliquota").value(creditos.get(1).getAliquota()))
                .andExpect(jsonPath("$[1].valorFaturado").value(creditos.get(1).getValorFaturado()))
                .andExpect(jsonPath("$[1].baseCalculo").value(creditos.get(1).getBaseCalculo()))
                .andDo(print());
    }

}

