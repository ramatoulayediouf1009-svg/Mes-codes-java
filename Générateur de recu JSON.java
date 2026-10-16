public class Main {

    public static String recuJson(String client,
                                  String[] articles,
                                  long[] prix,
                                  int[] quantites) {

        StringBuilder lignes = new StringBuilder();
        long totalGeneral = 0;

        for (int i = 0; i < articles.length; i++) {

            long sousTotal = prix[i] * quantites[i];
            totalGeneral += sousTotal;

            lignes.append("""
                    {
                      "libelle": "%s",
                      "quantite": %d,
                      "prixUnitaire": %d,
                      "sousTotal": %d
                    }""".formatted(
                    articles[i],
                    quantites[i],
                    prix[i],
                    sousTotal
            ));

            if (i < articles.length - 1) {
                lignes.append(",");
            }

            lignes.append("\n");
        }

        return """
                {
                  "client": "%s",
                  "lignes": [
                %s
                  ],
                  "totalGeneral": %d
                }
                """.formatted(client, lignes.toString(), totalGeneral);
    }

    public static void main(String[] args) {

        String[] articles = {"Riz", "Huile", "Sucre"};
        long[] prix = {1000, 1500, 800};
        int[] quantites = {2, 1, 3};

        String recu = recuJson(
                "Aida",
                articles,
                prix,
                quantites
        );

        System.out.println(recu);
    }
}
