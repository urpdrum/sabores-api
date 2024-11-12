package guia.saboresapi.domain.entity.validation.avaliacao;


import guia.saboresapi.domain.entity.Avaliacao;

public class AvaliacaoValidator {

    private AvaliacaoValidator() {
    }

    public static void validate(Avaliacao avaliacao) {
        //Valida se nota é de 0 a 5
        if (avaliacao.getNota() < 0 || avaliacao.getNota() > 5) {
            throw new IllegalArgumentException("A Nota deve ser entre 0 e 5");
        }
    }
}
