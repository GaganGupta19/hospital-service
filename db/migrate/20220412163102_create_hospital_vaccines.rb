class CreateHospitalVaccines < ActiveRecord::Migration[7.0]
  def change
    create_table :hospital_vaccines do |t|
      t.string :patient_phone
      t.string :vaccine_name
      t.integer :vendor_id
      t.integer :hospital_id

      t.timestamps
    end
  end
end
