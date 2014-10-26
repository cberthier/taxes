import spock.lang.*

class TaxesTest extends spock.lang.Specification {
    def "Taxe calcul"() {
        expect:
        produit.getTaxe() == taxe

        where:
        produit     																		| taxe
        new Produit(name: 'livre', price: 12.49, taxeRate: 0)  								| 0.0 
        new Produit(name: 'CD musical', price: 14.99, taxeRate: 0.10)  						| 1.5 
        new Produit(name: 'barre de chocolat', price: 0.85, taxeRate: 0)					| 0.0 
        new Produit(name: 'boite de chocolats importée', price: 10.00, taxeRate: 0.05)		| 0.5 
        new Produit(name: 'flacon de parfum importé', price: 47.50, taxeRate: 0.15)			| 7.15 
        new Produit(name: 'flacon de parfum importé', price: 27.99, taxeRate: 0.15)			| 4.2 
        new Produit(name: 'flacon de parfum', price: 18.99, taxeRate: 0.10)					| 1.9 
        new Produit(name: 'boite de pilules contre la migraine', price: 9.75, taxeRate: 0)	| 0.0 
        new Produit(name: 'boite de chocolats importé', price: 11.25, taxeRate: 0.05)		| 0.6 
    }
}  