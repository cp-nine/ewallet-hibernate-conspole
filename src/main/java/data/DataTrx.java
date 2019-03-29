package data;

import config.HibernateConfig;
import entities.TrxEntity;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class DataTrx {

    // get session factory connection (replacement DBConnection)
    private static SessionFactory factory = new HibernateConfig().getSessionFactory();

    public List<TrxEntity> getListTrans(Long acn){
        // open new session
        Session sesn = factory.openSession();

        // create temp variable for list user as array list
        List<TrxEntity> trxEntities = new ArrayList<TrxEntity>();
        try {
            // try to execute query and insert result to users list
            Query query = sesn.createQuery("FROM Transaction WHERE acn_debet= :debt OR  acn_credit= :crdt");
            query.setParameter("debt", acn);
            query.setParameter("crdt", acn);
            trxEntities = query.list();
        } catch (HibernateException e){
            e.printStackTrace();
        } finally {
            sesn.close();
        }
        return trxEntities;
    }

}
