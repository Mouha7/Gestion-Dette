@Override
    public boolean insert(T data) {
        boolean success = false;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(data);
            em.getTransaction().commit();
            success = true;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Échec de l'insertion : " + e.getMessage());
        } finally {
            closeEntityManager();
        }
        return success;
    }