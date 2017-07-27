-- SID
INSERT INTO acl_sid (id, principal, sid) VALUES (1, true, 'root');
INSERT INTO acl_sid (id, principal, sid) VALUES (2, true, 'GRP_APCN');

-- CLASS
INSERT INTO acl_class (id, class) VALUES (1, 'my.edu.umk.pams.account.system.model.AcModule');

-- object identity
INSERT INTO acl_object_identity (id, object_id_class, object_id_identity, parent_object, owner_sid, entries_inheriting) VALUES (1, 1, 2, null, 1, true);
INSERT INTO acl_object_identity (id, object_id_class, object_id_identity, parent_object, owner_sid, entries_inheriting) VALUES (2, 1, 1, null, 1, true);
INSERT INTO acl_object_identity (id, object_id_class, object_id_identity, parent_object, owner_sid, entries_inheriting) VALUES (4, 1, 3, null, 1, true);
INSERT INTO acl_object_identity (id, object_id_class, object_id_identity, parent_object, owner_sid, entries_inheriting) VALUES (5, 1, 6, null, 1, true);
INSERT INTO acl_object_identity (id, object_id_class, object_id_identity, parent_object, owner_sid, entries_inheriting) VALUES (6, 1, 5, null, 1, true);
INSERT INTO acl_object_identity (id, object_id_class, object_id_identity, parent_object, owner_sid, entries_inheriting) VALUES (3, 1, 4, null, 1, true);

-- entry
INSERT INTO acl_entry (id, acl_object_identity, ace_order, sid, mask, granting, audit_success, audit_failure) VALUES (1, 1, 0, 1, 1, true, false, false);
INSERT INTO acl_entry (id, acl_object_identity, ace_order, sid, mask, granting, audit_success, audit_failure) VALUES (2, 2, 0, 1, 1, true, false, false);
INSERT INTO acl_entry (id, acl_object_identity, ace_order, sid, mask, granting, audit_success, audit_failure) VALUES (4, 4, 0, 1, 1, true, false, false);
INSERT INTO acl_entry (id, acl_object_identity, ace_order, sid, mask, granting, audit_success, audit_failure) VALUES (5, 5, 0, 1, 1, true, false, false);
INSERT INTO acl_entry (id, acl_object_identity, ace_order, sid, mask, granting, audit_success, audit_failure) VALUES (6, 6, 0, 1, 1, true, false, false);
INSERT INTO acl_entry (id, acl_object_identity, ace_order, sid, mask, granting, audit_success, audit_failure) VALUES (7, 3, 0, 1, 1, true, false, false);
INSERT INTO acl_entry (id, acl_object_identity, ace_order, sid, mask, granting, audit_success, audit_failure) VALUES (8, 3, 1, 2, 1, true, false, false);