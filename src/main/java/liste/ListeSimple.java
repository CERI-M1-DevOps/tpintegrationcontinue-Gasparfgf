package liste;

public class ListeSimple {
    private long size;
    Noeud tete;

    /**
     * Retourne la taille actuelle de la liste.
     * @return La taille de la liste.
     */
    public long getSize() {
        return size;
    }

    /**
     * Ajoute un nouvel élément au début de la liste.
     * @param element L'élément à ajouter à la liste.
     */
    public void ajout(int element) {
        tete = new Noeud(element, tete);
        size++;
    }

    /**
     * Modifie le premier élément trouvé dans la liste.
     * @param element L'élément à rechercher.
     * @param nouvelleValeur La nouvelle valeur à attribuer au premier élément trouvé.
     */
    public void modifiePremier(Object element, Object nouvelleValeur) {
        Noeud courant = tete;
        while (courant != null && courant.getElement() != element)
            courant = courant.getSuivant();
        if (courant != null)
            courant.setElement(nouvelleValeur);
    }

    /**
     * Modifie tous les noeuds de la liste contenant l'élément spécifié.
     * @param element L'élément à rechercher dans la liste.
     * @param nouvelleValeur La nouvelle valeur à attribuer à chaque noeud trouvé contenant l'élément.
     */
    public void modifieTous(Object element, Object nouvelleValeur) {
        Noeud courant = tete;
        while (courant != null) {
            if (courant.getElement() == element)
                courant.setElement(nouvelleValeur);
            courant = courant.getSuivant();
        }
    }

    /**
     * Représente la liste sous forme de chaîne de caractères.
     * @return Une chaîne de caractères représentant tous les éléments de la liste.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder("ListeSimple(");
        Noeud n = tete;
        while (n != null) {
            sb.append(n);
            n = n.getSuivant();
            if (n != null)
                sb.append(", ");
        }
        sb.append(")");
        return sb.toString();
    }

    /**
     * Supprime le premier noeud de la liste contenant l'élément spécifié.
     * @param element L'élément à rechercher et supprimer dans la liste.
     */
    public void supprimePremier(Object element) {
        if (tete != null) {
            if (tete.getElement() == element) {
                tete = tete.getSuivant();
                size--;
                return;
            }
            Noeud precedent = tete;
            Noeud courant = tete.getSuivant();
            while (courant != null && courant.getElement() != element) {
                precedent = precedent.getSuivant();
                courant = courant.getSuivant();
            }
            if (courant != null) {
                precedent.setSuivant(courant.getSuivant());
                size--;
            }
        }
    }

    /**
     * Supprime tous les noeuds de la liste contenant l'élément spécifié.
     * @param element L'élément à rechercher et supprimer dans la liste.
     */
    public void supprimeTous(int element) {
       tete = supprimeTousRecurs(element, tete);
    }

    /**
     * Supprime tous les noeuds contenant l'élément spécifié en utilisant une méthode récursive.
     * @param element L'élément à rechercher et supprimer dans la liste.
     * @param tete Le noeud de départ de la sous-liste à traiter.
     * @return Le nouveau noeud de tête après la suppression.
     */
    public Noeud supprimeTousRecurs(Object element, Noeud tete) {
        if (tete != null) {
            Noeud suiteListe = supprimeTousRecurs(element, tete.getSuivant());
            if (tete.getElement() == element) {
                size--;
                return suiteListe;
            } else {
                tete.setSuivant(suiteListe);
                return tete;
            }
        } else return null;
    }

    /**
     * Retourne l'avant-dernier noeud de la liste.
     * @return L'avant-dernier noeud de la liste, ou null si la liste contient moins de deux noeuds.
     */
    public Noeud getAvantDernier() {
        if (tete == null || tete.getSuivant() == null)
            return null;
        else {
            Noeud courant = tete;
            Noeud suivant = courant.getSuivant();
            while (suivant.getSuivant() != null) {
                courant = suivant;
                suivant = suivant.getSuivant();
            }
            return courant;
        }
    }

    /**
     * Inverse l'ordre des noeuds dans la liste.
     */
    public void inverser() {
        Noeud precedent = null;
        Noeud courant = tete;
        while (courant != null) {
            Noeud next = courant.getSuivant();
            courant.setSuivant(precedent);
            precedent = courant;
            courant = next;
        }
        tete = precedent;
    }

    /**
     * Retourne le noeud précédant le noeud spécifié dans la liste.
     * @param r Le noeud dont le précédent est à rechercher.
     * @return Le noeud précédant le noeud spécifié.
     */
    public Noeud getPrecedent(Noeud r) {
    // la liste n'est pas vide puisqu'on transmet un Node de la liste et le Node existe obligatoirement
        Noeud precedent = tete;
        Noeud courant = precedent.getSuivant();
        while (courant != r) {
            precedent = courant;
            courant = courant.getSuivant();
        }
        return precedent;
    }

    /**
     * Échange les positions de deux noeuds dans la liste.
     * @param r1 Le premier noeud à échanger.
     * @param r2 Le second noeud à échanger.
     */
    public void echanger(Noeud r1, Noeud r2) {

        if (r1 == r2 || r1 == null || r2 == null)
            return;

        Noeud precedentR1 = r1 != tete ? getPrecedent(r1) : null;
        Noeud precedentR2 = r2 != tete ? getPrecedent(r2) : null;


        if (r1 == tete)
            tete = r2;
        else if (r2 == tete)
            tete = r1;
    
        if (precedentR1 != null)
            precedentR1.setSuivant(r2);
        
        if (precedentR2 != null)
            precedentR2.setSuivant(r1);

    
        Noeud temp = r1.getSuivant();
        r1.setSuivant(r2.getSuivant());
        r2.setSuivant(temp);
    }

}