import {useState} from "react";
import axios from "axios";

const Investment = () => {
    const currentDate = new Date();

    const [name, setName] = useState('');
    const [contractDate, setContractDate] = useState(currentDate.toISOString().slice(0, 10));
    const [period, setPeriod] = useState(24);
    const [amount, setAmount] = useState(200_000);
    const [interestRate, setInterestRate] = useState(5);
    const [scheduleType, setScheduleType] = useState("MONTHLY");
    const [investmentValue, setInvestmentValue] = useState(100_000);
    const [ownContribution, setOwnContribution] = useState(20_000);
    const [comission, setComission] = useState(3);

    const handleSubmit = (e) => {
        console.log("handle submit");
        e.preventDefault();

        if (scheduleType === "QUARTERLY" && period % 3 !== 0) {
            console.log("Nieprawidłowy okres spłaty");
            alert("Okres kredytowania musi być podzielny przez 3 w przypadku harmonogramu kwartalnego");
            return;
        }
        if (parseFloat(investmentValue) < parseFloat(ownContribution)) {
            console.log(investmentValue);
            console.log(ownContribution);
            console.log(typeof investmentValue);
            console.log(typeof ownContribution);
            alert("Wartość własnego wkładu nie może być większa niż wartość inwestycji");
            return;
        }
        if (parseFloat(amount) < parseFloat(ownContribution)) {
            alert("Wartość własnego wkładu nie może być większa niż wartość kredytu");
            return;
        }
        const schedule = getSchedule();

    }

    const getSchedule = async () => {
        const schedule = {
            name,
            contractDate,
            period,
            amount,
            interestRate,
            scheduleType,
            investmentValue,
            ownContribution,
            comission
        };
        console.log("get schedule");
        console.log(schedule);

        axios.post(
            'http://localhost/api/investment',
            schedule,
            {responseType: 'blob'})
            .then(res => printSchedule(res.data));
    }

    const printSchedule = (schedule) => {
        console.log(schedule);
        window.open(URL.createObjectURL(schedule));
        console.log("print schedule");
    }

    return (
        <div className="agro">
            {/*<h2>Kredyt inwestycyjny</h2>*/}
            <form onSubmit={handleSubmit}>

                <label htmlFor="name">Imię i nazwisko:</label>
                <input type="text"
                       id="name"
                       value={name}
                       onChange={
                           (e) => {
                               const re = /^[a-zA-ZżźćńółęąśŻŹĆĄŚĘŁÓŃ ]+$/;
                               if (e.target.value === "" || re.test(e.target.value)) {
                                   setName(e.target.value)
                               }
                               ;
                           }}
                       required
                />
                <label>Data zawarcia umowy</label>
                <input type="date" lang="pl-PL"
                       min={currentDate.toISOString().slice(0, 10)}
                       value={contractDate}
                       onChange={(e) => setContractDate(e.target.value)}
                       required/>
                <label>Okres finansowania (w miesiącach)</label>
                <input type="number"
                       value={period}
                       onChange={
                           (e) => {
                               const re = /^[1-9][0-9]{0,8}$/;
                               if (e.target.value === "" || re.test(e.target.value)) {
                                   setPeriod(e.target.value)
                               }
                               ;
                           }}
                       required/>

                <label>Wartość inwestycji</label>
                <input type="float"
                       value={investmentValue}
                       onChange={
                           (e) => {
                               const re = /^[1-9][0-9]*[\.,]{0,1}[0-9]{0,2}$/;
                               if (e.target.value === "" || re.test(e.target.value)) {
                                   setInvestmentValue(e.target.value.replace(",", "."));
                               }
                               ;
                           }}
                       required/>
                <label>Wkład własny</label>
                <input type="float"
                       value={ownContribution}
                       onChange={
                           (e) => {
                               const re = /^[1-9][0-9]*[\.,]{0,1}[0-9]{0,2}$/;
                               if (e.target.value === "" || re.test(e.target.value)) {
                                   setOwnContribution(e.target.value.replace(",", "."));

                               }
                               ;
                           }}
                       required/>
                <label>Kwota kredytu</label>
                <input type="float"
                       value={amount}
                       onChange={
                           (e) => {
                               const re = /^[1-9][0-9]*[\.,]{0,1}[0-9]{0,2}$/;
                               if (e.target.value === "" || re.test(e.target.value)) {

                                   setAmount(e.target.value.replace(",", "."));

                               }
                               ;
                           }}
                       required/>
                <label>Oprocentowanie kredytu</label>
                <input type="float"
                       value={interestRate}
                       onChange={
                           (e) => {
                               const re = /^[0-9]*[\.,]{0,1}[0-9]{0,3}$/;
                               if (e.target.value === "" || re.test(e.target.value)) {

                                   setInterestRate(e.target.value.replace(",", "."));

                               }
                               ;
                           }}
                       required/>
                <label>Prowizja</label>
                <input type="float"
                       value={comission}
                       onChange={
                           (e) => {
                               const re = /^[0-9]*[\.,]{0,1}[0-9]{0,3}$/;
                               if (e.target.value === "" || re.test(e.target.value)) {

                                   setComission(e.target.value.replace(",", "."));

                               }
                               ;
                           }}
                       required/>
                <label>Typ harmonogramu spłat</label>
                <select
                    value={scheduleType}
                    onChange={(e) => setScheduleType(e.target.value)}>
                    <option value="MONTHLY">Miesięczny</option>
                    <option value="QUARTERLY">Kwartalny</option>
                </select>
                <button>Oblicz</button>


            </form>

        </div>
    );
}

export default Investment;